package UploadDownloadController;

import com.sun.xml.internal.bind.v2.TODO;

/**
 * this class is used to find the directory of current user in disk
 */
public interface IDirManager {

    /**
     * Create a folder
     * @param UserId
     * @param path   path folder created.
     * @return  true if create successfully.
     */
    public boolean createDir(int UserId, String path);

    /**
     * get the request to delete a folder
     * delete a folder
     * @param UserId
     * @param path
     * @return
     */
    public void deleteDir(int UserId, String path);

    /**
     * get the user's request of deleting file.
     * Delete a file from the current table, and sent info to retrive table
     * @param UserId
     * @param path
     */
    public void deleteFile(int UserId, String path);

    //TODO: if we need to retrive five actions of users deletion?
}
