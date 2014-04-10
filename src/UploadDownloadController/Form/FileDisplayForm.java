package UploadDownloadController.Form;

import org.apache.struts.action.ActionForm;

/**
 * This is a Form class to show the file directory tree into the homepage.
 */
public class FileDisplayForm extends ActionForm{
    private String userName;
    private String passWord;

    /**
     * user name getter
     * @param userName new userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * password setter
     * @param passWord new password
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * username getter
     * @return  username
     */
    public String getUserName() {

        return userName;
    }

    /**
     * password getter
     * @return password
     */
    public String getPassWord() {
        return passWord;
    }
}
