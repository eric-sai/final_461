//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package UserManagement.action;

import UserManagement.form.LoginForm;
import UserManagement.hibernate.UserDAO;
import UserManagement.session.AuthorityUtil;
import model.SessionEntity;
import model.UserEntity;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * this class is to put the information of user in the session and check the validity of user's information
 */
public class LoginAction extends BaseAction {
    /**
     * this method is to put the information of user in the session and check the validity of user's information
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return    forward to the page of homepage and if user information is not valid, return to the page of login
     * @throws Exception
     */


	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) throws Exception{


        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma","no-cache");


        LoginForm loginForm = (LoginForm)form;
		UserDAO dao = new UserDAO();
		UserEntity user = dao.getUser( loginForm.getName() );

		if( user!=null && user.getPassword().equals( loginForm.getPassword() ) ) {

            Date dt=new Date();
            SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
            String time=matter.format(dt);
            if(!dao.getBytime(time,user.getuId()))
            {
            SessionEntity userlogin= new SessionEntity();
            userlogin.setUser(user);
            userlogin.setSessiontime(time);
            user.getSessions().add(userlogin);
            dao.updateUser(user);
            }
			AuthorityUtil.saveUser(request, user);
			return mapping.findForward( "homepage" );
		}

		addMessage( request, "logon.failed" );
		return mapping.getInputForward();
	}

}

