package UserManagement.action;

import UserManagement.form.ResetPasswordForm;
import UserManagement.hibernate.UserDAO;
import UserManagement.session.AuthorityUtil;
import UserManagement.session.UserInfo;
import model.UserEntity;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *   this class is to reset the password
 */
public class ResetPasswordAction extends BaseAction {
    /**
     * this method is to reset the password
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return            return to the page of homepage
     * @throws Exception
     */
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	        throws Exception{


        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma","no-cache");


        UserInfo ui = AuthorityUtil.getUser(request);
        if( ui==null ) {
            addMessage( request, "error.login.first" );
            return mapping.findForward( "login" );
        }
        UserDAO dao = new UserDAO();
        UserEntity user = dao.getUserById( ui.getUserId());
        ResetPasswordForm reset = (ResetPasswordForm) form;
        if(reset.getOriginalPassword().equals(user.getPassword()))
        {
         user.setPassword(reset.getNewPassword());
         dao.update(user);
         return mapping.findForward("homepage");
        }
        else
        {
         addMessage( request, "reset.failed" );
         return mapping.getInputForward();
        }
	}


}
