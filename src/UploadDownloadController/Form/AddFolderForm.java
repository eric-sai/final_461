package UploadDownloadController.Form;

import org.apache.struts.action.ActionForm;

/**
 * The action form of the add folder action
 */
public class AddFolderForm extends ActionForm{
    private String folderName;
    private String folderPath;
    private String userId;

    /**
     * user id getter
     * @return user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * user id setter
     * @param userId new user id
     */

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *  partial folder path getter
     * @return  partial folder path
     */
    public String getFolderPath() {
        return folderPath;
    }

    /**
     * partial folder path setter
     * @param folderPath new partial folder path
     */

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    /**
     *folder name setter
     * @param folderName new folder name
     */
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    /**
     * folder name getter
     * @return folder name
     */
    public String getFolderName() {

        return folderName;
    }
}
