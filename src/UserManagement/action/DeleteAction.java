//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package UserManagement.action;

import UserManagement.form.IdForm;
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
 *
 *
 * this class is to execute the operation of deleting a user
 * Method execute
 */

public class DeleteAction extends BaseAction {


	/**
     * this class is to execute the operation of deleting a user
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward       forward to the page of manage
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


        IdForm idform = (IdForm) form;
        UserDAO dao = new UserDAO();

        int id= Integer.parseInt(idform.getId());
        UserEntity user=dao.getUserById(id);
        if(user.getRole()==1)
        {
            addMessage( request, "error.admin" );
            return mapping.findForward( "manage" );
        }
        else {
        dao.deleteUser(id);   }

		return mapping.findForward( "manage" );
	}

}

