//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package UserManagement.form;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;

/**
 *  this is login form that show user login page
 */
public class LoginForm extends ValidatorForm {


	private String password;
	private String name;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		this.password = null;
		this.name = null;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

