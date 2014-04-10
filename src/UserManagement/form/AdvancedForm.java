package UserManagement.form;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;

/**
 *  this class is to save the information of the search conditions input by the user
 */
public class AdvancedForm extends ValidatorForm {

	private String name;
    private String gender;
    private String year;
    private String month;
    private String day;
    private String email;
    private String address;
    private String phone;
    private String role;
    private String rank;


    /**
     * set the attributes as null
     * @param mapping
     * @param request
     */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.name = null;
		this.role = null;
        this.rank = null;
        this.gender = null;
        this.year =null;
        this.day =null;
        this.email = null;
        this.address = null;
	}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * get year
     * @return
     */
    public String getYear() {
        return year;
    }

    /**
     * set year
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * get month
     * @return
     */
    public String getMonth() {
        return month;
    }

    /**
     * set month
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * get day
     * @return
     */
    public String getDay() {
        return day;
    }

    /**
     * set day
     * @param day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * get email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * set email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * set address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get gender
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * set gender
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * get phone
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * set phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * get role
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     * set role
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * get rank
     * @return
     */
    public String getRank() {
        return rank;
    }

    /**
     * set rank
     * @param rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }
}