package UserManagement.action;

import UserManagement.form.RegisterForm;
import UserManagement.hibernate.UserDAO;
import UserManagement.session.AuthorityUtil;
import model.UserEntity;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *   this class is to save the information registered by the user
 */

public class RegisterAction extends BaseAction {

    /**
     * this method is to save the information registered by the user
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return        forward to the pape of login
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


        RegisterForm addUserForm = (RegisterForm) form;// TODO Auto-generated method stub
		UserEntity user = new UserEntity();
		user.setName(addUserForm.getName());
		user.setPassword(addUserForm.getPassword());
        user.setEmail(addUserForm.getEmail());
        user.setAddress(addUserForm.getAddress());
        user.setGender(addUserForm.getGender());
        user.setPhone(addUserForm.getPhone());
        user.setBirthday(addUserForm.getYear()+"-"+addUserForm.getMonth()+"-"+addUserForm.getDay());
        user.setRank(0);
        Date dt=new Date();
        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
        String time=matter.format(dt);
        user.setCreatedate(time);
        user.setRole(0);
        UserDAO usersDAO = new UserDAO();
		usersDAO.saveUser(user);
        AuthorityUtil.saveUser(request, user);



        if (isCancelled(request)){
            return mapping.findForward("login");
        }

		return mapping.findForward("login");
	}


}
