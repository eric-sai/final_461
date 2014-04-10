package UploadDownloadController;

/**
 * this interface is used to share the files between user
 */
public interface IFileShare {

    /**
     * get a request from user to share a file with another user.
     * @param shareFromUserId
     * @param shareToUserId
     * @param FileId
     * @return      true if share information correctly.
     */
    public boolean shareFileWith(int shareFromUserId, int shareToUserId, int FileId);

    /**
     * get a request to unshare a file
     * @param shareFromUserId
     * @param shareToUserId
     * @param FileId
     * @return         true if unshare successfully.
     */
    public boolean unShareFileWith(int shareFromUserId, int shareToUserId, int FileId);

    /**
     * set if one file share to all.
     * @param userId
     * @param FileId
     * @param isPublic        true if set file to public
     */
    public void setPublicFile(int userId, int FileId, boolean isPublic);
}
