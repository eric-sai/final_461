//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package UserManagement.action;

import UserManagement.form.EditForm;
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
 *     this class is to save the information edited by the user
 */

public class EditSaveAction extends BaseAction {


	/** 
	 * Method execute
     * this method is to save the information edited by the user
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward    forward to the page of homepage
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


        UserInfo ui = AuthorityUtil.getUser(request);
        if( ui==null ) {
            addMessage( request, "error.login.first" );
            return mapping.findForward( "login" );
        }
        UserDAO dao = new UserDAO();
        UserEntity user = dao.getUserById( ui.getUserId());
		EditForm edit = (EditForm) form;
        user.setName(edit.getName());
        user.setBirthday(edit.getYear()+"-"+edit.getMonth()+"-"+edit.getDay());
        user.setGender(edit.getGender());
        user.setEmail(edit.getEmail());
        user.setAddress(edit.getAddress());
        user.setPhone(edit.getPhone());
        dao.update(user);
		return mapping.findForward( "homepage" );
	}

}

