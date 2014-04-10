//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package UserManagement.action;

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
 *    this class is to edit the information of users and show the information of specific user for edit
 */

public class EditAction extends BaseAction {


	/**
     * this method is to edit the information of users and show the information of specific user for edit
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward    forward page to the edit page
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) throws Exception {


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
		request.setAttribute( "user", user );
        String birthday=user.getBirthday();
        String year=birthday.substring(0,4);
        String month=birthday.substring(5,7);
        String day=birthday.substring(8,10);
        request.setAttribute( "year", year );
        request.setAttribute( "month", month );
        request.setAttribute( "day", day );
		
		return mapping.findForward( "edit" );
	}

}

