package historyMachineController.Form;

import org.apache.struts.action.ActionForm;

/**
 * this form show user the file id
 */
public class GetFileBackFrom extends ActionForm{
    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    private String fid;
}
