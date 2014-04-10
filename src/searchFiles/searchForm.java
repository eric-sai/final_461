package searchFiles;

import org.apache.struts.action.ActionForm;
/**
 * this class is used to show the user with search form
 */
public class searchForm extends ActionForm{
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    private String filename;

}
