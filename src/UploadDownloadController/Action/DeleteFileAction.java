package UploadDownloadController.Action;

import UploadDownloadController.DataManage;
import UploadDownloadController.Form.DeleteFileForm;
import UploadDownloadController.Helper.TreeNode;
import UserManagement.hibernate.UserDAO;
import UserManagement.session.AuthorityUtil;
import UserManagement.session.UserInfo;
import historyMachineController.FileDirectionary;
import model.UserEntity;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Action for delete file and folder action.
 */
public class DeleteFileAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DeleteFileForm deleteFileForm = (DeleteFileForm) form;

        int isFolder = Integer.parseInt(deleteFileForm.getIsFolder());

        System.out.println("isFolder: "+isFolder);

        String userId = deleteFileForm.getUserId();
        if (isFolder == 0) {
            int fileId = Integer.parseInt(deleteFileForm.getFid());
            DataManage dm = new DataManage();
            System.out.println("fileId in delete Action:" + fileId);
            dm.deleteFile(fileId,Integer.parseInt(userId));
        }
        //delete folder.
        else {
            String folderID = deleteFileForm.getFolderID();

            System.out.println("folderID: " + folderID);

            DataManage dm = new DataManage();

            if(!folderID.equals("-1"))
            {
                dm.deleteFolder(Integer.parseInt(folderID),Integer.parseInt(userId));
            }
            else //folderID = -1
            {
                dm.deleteFolder(-1,Integer.parseInt(userId));
            }

        }


        UserInfo ui = AuthorityUtil.getUser(request);
        if (ui == null) {
//            addMessage( request, "error.login.first" );
            return mapping.findForward("manage");
        }

        UserDAO dao = new UserDAO();
        UserEntity user = dao.getUserById(ui.getUserId());
        dao.checkRank(user);


        FileDirectionary fileDirectionary = new FileDirectionary();
        System.out.println("UserId in DeletedFileAction: " + userId);
        TreeNode root = fileDirectionary.buildTree(userId);


        request.setAttribute("filePathTestTree", root);
        request.setAttribute("user", user);
        request.setAttribute("userId", String.valueOf(user.getuId()));
        request.setAttribute("userName", String.valueOf(user.getName()));
        Session session = null;
                     Configuration configuration=new Configuration().configure();
                     ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
                     SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                     session =sessionFactory.openSession();
                     session.beginTransaction();
                     Query q = session.createQuery("select  distinct new list(f.filename,d.directoryTree) from FileEntity f,FileDirEntity d where f.fid = d.fid and f.shared=1");
                     List<List> list_f = q.list();
                     session.getTransaction().commit();
                     session.close();
                     request.setAttribute("share",list_f);
        return mapping.findForward("success");

    }

}
