package UserManagement.session;

import model.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * this method can author user's login
 */
public class AuthorityUtil {

	public static final String SESSION_USERINFO = "authority.userinfo";
	
	public static void saveUser( HttpServletRequest request, UserEntity user ) {
		
		if( user==null ) return;
		
		UserInfo ui = getUser( request );
		
		if( ui==null ) ui = new UserInfo();
		
		ui.setUserId( user.getuId());
		saveUser( request, ui );
	}

    /**
     * save current user
     * @param request the http request
     * @param user the user
     */
	public static void saveUser( HttpServletRequest request, UserInfo user ) {
		HttpSession session = request.getSession();
		session.setAttribute( SESSION_USERINFO, user );
	}

    /**
     * get user from database with the info of current user
     * @param request http request
     * @return user info
     */
	public static UserInfo getUser( HttpServletRequest request ) {
		HttpSession session = request.getSession();
		return (UserInfo)session.getAttribute( SESSION_USERINFO );
	}

    /**
     * remove a user in database
     * @param request   http request
     */
    public static void removeUser( HttpServletRequest request ) {
        HttpSession session = request.getSession();
        session.removeAttribute(SESSION_USERINFO);
        session.invalidate();

    }
}
