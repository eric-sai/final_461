package UploadDownloadController.Form;

import org.apache.struts.action.ActionForm;

/**
 * The action form for Share action.
 */
public class ShareFileForm extends ActionForm {
    /**
     * if is share a folder
     */
    private String isFolder;

    /**
     * get fid of a folder
     * @return  fid of a folder, -1 if is root or is a file
     */
    public String getFolderID() {
        return folderID;
    }

    /**
     * set the folder's fid
     * @param folderID fid of the folder
     */
    public void setFolderID(String folderID) {
        this.folderID = folderID;
    }

    /**
     * fid of the folder
     */
    private String folderID;

    /**
     * get the user id
     * @return user id who do this action
     */
    public String getUserId() {
        return userId;
    }

    /**
     * set the user id
     * @param userId   uid of who share the file
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * the user id of the action
     */
    private String userId;

    /**
     * get fid of the file
     * @return fid of the shared file, -1 if is shared a folder
     */
    public String getFid() {
        return fid;
    }

    /**
     * set the fid of the file
     * @param fid  fid of the file, -1 if is shared a folder
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * fid of the shared file
     */
    private String fid;

    /**
     * get if is shared a folder
     * @return  1 if is a folder, 0 if is a file
     */
    public String getIsFolder() {
        return isFolder;
    }

    /**
     * set if is shared a folder
     * @param folder  1 if is a folder, 0 if is a file.
     */
    public void setIsFolder(String folder) {
        isFolder = folder;
    }
}
