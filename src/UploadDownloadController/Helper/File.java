package UploadDownloadController.Helper;

/**
 * the file class which contain the setter and getter of File
 */
public class File {
    private String fileName;
    private boolean shared;
    private String type;
    private String size;
    private String filePath;
    private String created;
    public File() {
        fileName="";
        shared=false;
        type="";
        filePath="";
        created="";
        size="";
    }

    public File(String fileName, boolean shared, String type, String filePath,String created,String size) {
        this.fileName = fileName;
        this.shared = shared;
        this.type = type;
        this.filePath = filePath;
        this.created=created;
        this.size=size;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isShared() {
        return shared;
    }



    public String getFilePath() {
        return filePath;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }



    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
