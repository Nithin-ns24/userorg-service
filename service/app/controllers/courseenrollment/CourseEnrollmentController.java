package controllers.courseenrollment;

import app.controllers.courseenrollment.validator.CourseEnrollmentRequestValidator;
import controllers.BaseController;
import org.sunbird.common.models.util.ActorOperations;
import org.sunbird.common.models.util.JsonKey;
import org.sunbird.common.request.Request;
import play.libs.F.Promise;
import play.mvc.Result;

public class CourseEnrollmentController extends BaseController {

  public Promise<Result> getEnrolledCourses(String uid) {
    return handleRequest(
        ActorOperations.GET_COURSE.getValue(),
        request().body().asJson(),
        null,
        JsonKey.USER_ID,
        uid,
        getAllRequestHeaders((request())),
        false);
  }

  public Promise<Result> enrollCourse() {
    return handleRequest(
        ActorOperations.ENROLL_COURSE.getValue(),
        request().body().asJson(),
        (request) -> {
          new CourseEnrollmentRequestValidator().validateEnrollCourse((Request) request);
          return null;
        },
        getAllRequestHeaders(request()));
  }

  public Promise<Result> unenrollCourse() {
    return handleRequest(
        ActorOperations.UNENROLL_COURSE.getValue(),
        request().body().asJson(),
        (request) -> {
          new CourseEnrollmentRequestValidator().validateUnenrollCourse((Request) request);
          return null;
        },
        getAllRequestHeaders(request()));
  }
}