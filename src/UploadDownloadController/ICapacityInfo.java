package UploadDownloadController;

/**
 * this class is used to check the capacity of file
 */
public interface ICapacityInfo {

    /**
     * get user capacity
     * @param UserId
     * @return    user capacity in KB.
     */
    public int getUserCapacity(int UserId);

}
