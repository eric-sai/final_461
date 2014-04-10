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
 * this class is to execute the operation of setting an average user to be administrator
 */
public class AdminAction extends BaseAction {
    /**
     * this method is to set an average user to be administrator
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return         forwarding page to the page of manage page
     * @throws Exception
     */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) throws Exception {

        UserInfo ui = AuthorityUtil.getUser(request);
        if( ui==null ) {
            addMessage( request, "error.login.first" );
            return mapping.findForward( "login" );
        }

        IdForm idform = (IdForm) form;
        UserDAO dao = new UserDAO();
        int id= Integer.parseInt(idform.getId());
        UserEntity user=dao.getUserById(id);
        user.setRole(1);
        dao.update(user);
		
		return mapping.findForward( "manage" );
	}

}

