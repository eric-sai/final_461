//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package UserManagement.action;

import UserManagement.form.AdvancedForm;
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
 *   this class is used to do the advanced search
 */
public class AdvancedAction extends BaseAction {



	/** 
	 * Method execute
     * this method is used to do the advanced search
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward       forword to the advanced search page
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
            return mapping.findForward( "manage" );
        }


        AdvancedForm ad=(AdvancedForm)form;
        String name=ad.getName();
        String gender=ad.getGender();
        String birthday=ad.getYear()+"-"+ad.getMonth()+"-"+ad.getDay();
        System.out.println(birthday);
        String email=ad.getEmail();
        String address=ad.getAddress();
        String phone=ad.getPhone();
        String role=ad.getRole();
        String rank=ad.getRank();
        if(name.equals("")&&gender.equals("")&&birthday.equals("0000-00-00")&&email.equals("")&&
           address.equals("")&&phone.equals("")&&role.equals("")&&rank.equals(""))
        {
            addMessage( request, "error.input.all" );
            return mapping.findForward( "advancedSearch" );
        }
        if((ad.getYear().equals("0000")||ad.getMonth().equals("00")||ad.getDay().equals("00"))
                &&(!birthday.equals("0000-00-00")))
        {
            addMessage( request, "error.input.birthday" );
            return mapping.findForward( "advancedSearch" );
        }
        UserDAO dao = new UserDAO();
        List<UserEntity> list=dao.advancedSearch(name,gender,birthday,email,address,phone,role,rank);
        if(list.isEmpty()){
            addMessage( request, "error.search" );
            return mapping.findForward( "advancedSearch" );
        }
        request.setAttribute("list", list);
		return mapping.findForward( "advancedSearch" );
	}

}

