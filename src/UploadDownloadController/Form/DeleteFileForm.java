package UploadDownloadController.Form;

import org.apache.struts.action.ActionForm;

/**
 * The action form for delete action
 */
public class DeleteFileForm extends ActionForm {
    /**
     * if the delete item is file
     */
    private String isFolder;

    /**
     * get fid of the folder
     * @return    fid of the folder, -1 if delte a file or the root
     */
    public String getFolderID() {
        return folderID;
    }

    /**
     * set fodler id
     * @param folderID fid of the folder
     */
    public void setFolderID(String folderID) {
        this.folderID = folderID;
    }

    /**
     * fid of the folder, -1 if it is not a folder
     */
    private String folderID;

    /**
     * user id
     * @return  user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * set user id
     * @param userId   user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * the user id who delete the file
     */
    private String userId;

    /**
     * get fid of  a file
     * @return  fid of the file,  -1 if delete a folder
     */
    public String getFid() {
        return fid;
    }

    /**
     * set fid of a file
     * @param fid   fid of a file
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * fid of a file is being deleted, -1 if delete a folder
     */
    private String fid;

    /**
     * get to know if is delete a folder
     * @return 1 if delete a folder, 0 if delete a file
     */
    public String getIsFolder() {
        return isFolder;
    }

    /**
     * set if delete a folder
     * @param folder  1 if delete a folder, 0 if delete a file
     */
    public void setIsFolder(String folder) {
        isFolder = folder;
    }
}
