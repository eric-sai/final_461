//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package UserManagement.form;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;

/**
 * this form is used to find user id and reset it
 */
public class IdForm extends ValidatorForm {

	// --------------------------------------------------------- Instance Variables

	/** id property */
	private String id;

	// --------------------------------------------------------- Methods

	/**
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// TODO Auto-generated method stub
	}

	/**
	 * Returns the id.
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the id.
	 * @param id The id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}

