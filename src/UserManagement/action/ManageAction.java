//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package UserManagement.action;

import UserManagement.form.ManageForm;
import UserManagement.hibernate.UserDAO;
import UserManagement.session.AuthorityUtil;
import UserManagement.session.UserInfo;
import model.UserEntity;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *  this class is to administrate all the users  and show all the information of users
 */


public class ManageAction extends BaseAction {


	/** 
	 * Method execute
     * this method is to administrate all the users  and show all the information of users
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

        ManageForm mf= (ManageForm)form;
		UserDAO dao = new UserDAO();
        String name= mf.getName();
        if(name!=null)
        {
            if(name.equals(""))
            {
                addMessage( request, "error.name.null" );
                List<UserEntity> list=dao.allUsers();
                request.setAttribute("list", list);
                return mapping.findForward( "manage" );
            }
        UserEntity userq=dao.getUserByName(name);
            if(userq==null)
            {
                addMessage( request, "error.nobody" );
                return mapping.findForward( "manage" );
            }
        request.setAttribute("userq", userq);
        }
        if(name==null)
        {
        List<UserEntity> list=dao.allUsers();
		request.setAttribute("list", list);

        }
		return mapping.findForward( "manage" );
	}

}

