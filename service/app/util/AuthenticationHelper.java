/**
 * 
 */
package util;

import org.sunbird.cassandra.CassandraOperation;
import org.sunbird.common.models.util.JsonKey;
import org.sunbird.common.models.util.ProjectLogger;
import org.sunbird.common.models.util.PropertiesCache;
import org.sunbird.helper.ServiceFactory;
import org.sunbird.learner.util.Util;
import org.sunbird.learner.util.Util.DbInfo;
import org.sunbird.services.sso.SSOManager;
import org.sunbird.services.sso.SSOServiceFactory;

/**
 * 
 * This class will handle all the method related to authentication. For example verifying user
 * access token, creating access token after success login.
 * 
 * @author Manzarul
 *
 */
public class AuthenticationHelper {
  
  private CassandraOperation cassandraOperation = ServiceFactory.getInstance();
  DbInfo userAuth = Util.dbInfoMap.get(JsonKey.USER_AUTH_DB);
  
  /**
   * This method will verify the incoming user access token against store data base /cache. If token
   * is valid then it would be associated with some user id. In case of token matched it will
   * provide user id. else will provide empty string.
   * @param token String
   * @return String
   */
  public static String verifyUserAccesToken(String token) {
    SSOManager ssoManager = SSOServiceFactory.getInstance();
    String userId = ""; 
    try { 
      boolean response = Boolean.parseBoolean(PropertiesCache.getInstance().getProperty(JsonKey.IS_SSO_ENABLED));
      if (response) {
      userId = ssoManager.verifyToken(token);
      } else {
        userId = token;
      }
    } catch (Exception e) {
      ProjectLogger.log("invalid auth token =" + token, e);
    }
    return userId;
  }

  /**
   * This method will save the user access token in side data base.
   * 
   * @param token String
   * @param userId String
   * @return boolean
   */
  public static boolean saveUserAccessToken(String token, String userId) {
    
    return false;
  }

  /**
   * This method will invalidate the user access token.
   * 
   * @param token String
   * @return boolean
   */
  public static boolean invalidateToken(String token) {
    
    return false;
  }
}
