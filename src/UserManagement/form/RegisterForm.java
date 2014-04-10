package UserManagement.form;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;

/**
 * this is the register form that show the page of register
 */
public class RegisterForm extends ValidatorForm {

	private String name;
	private String password;
    private String repassword;
    private String gender;
    private String year;
    private String month;
    private String day;
    private String email;
    private String address;
    private String phone;



	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.name = null;
		this.password = null;
        this.repassword = null;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }



    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}