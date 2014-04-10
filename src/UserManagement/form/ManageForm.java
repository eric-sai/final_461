//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package UserManagement.form;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;


public class ManageForm extends ValidatorForm {

	private String name;
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		this.name = null;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

