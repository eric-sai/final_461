package UploadDownloadController.Form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
/**
 * This is a Form class to implement the file upload action..
 */
public class FileUploadForm extends ActionForm{

    private FormFile file;
    private String userId;
    private String filePath;

    /**
     * file path setter
     * @param filePath new partial file path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * partial file path getter
     * @return partial file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * user id setter
     * @param userId new user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * user id setter
     * @return user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * file getter
     * @return the file(except folders) including in this folder
     */
    public FormFile getFile() {
        return file;
    }

    /**
     * file setter
     * @param file new file(except folders) added into this folder
     */
    public void setFile(FormFile file) {
        this.file = file;
    }

    /**
     * error actor,if the uploading file is more than 1gb, return an error.
     * @param mapping mapping info
     * @param request request seeter and getter
     * @return error actions, if the uploading file is more than 1gb, return an error.
     */
    @Override
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();

        System.out.println(getFile().getFileSize());
        if(getFile().getFileSize() > 102400000){ //1Gb
            errors.add("common.file.err.size",
                    new ActionMessage("error.common.file.size.limit", 102400));
            return errors;
        }

        return errors;
    }

}