package UploadDownloadController.Action;


import UploadDownloadController.DataManage;
import UploadDownloadController.Form.FileUploadForm;
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
import org.apache.struts.upload.FormFile;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;

/**
 * The Action of the file upload.This class take the file info and upload it into the space, and insert such info into
 * the database, then map user to the homepage.
 */
public class FileUploadAction extends Action{
    /**
     * Action body of the class
     * @param mapping mapping info
     * @param form FileUploadActionForm
     * @param request request setter and getter
     * @param response response setter and getter
     * @return the mapping info
     * @throws Exception
     */

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        FileUploadForm fileUploadForm = (FileUploadForm)form;

        String userId=fileUploadForm.getUserId();
        String userFilePath=fileUploadForm.getFilePath();

        String realFilepath=getServlet().getServletContext().getRealPath("/");
        realFilepath=realFilepath.substring(0,realFilepath.indexOf("branches")+9)+"out/artifacts/COMS461_war_exploded";

        FormFile file = fileUploadForm.getFile();
        //Get the servers upload directory real path name


        String filePath = realFilepath+"/downloadFiles"+userFilePath;
        //create the upload folder if not exists
        File folder = new File(filePath);
        if(!folder.exists()){
            folder.mkdir();
        }

        String fileName = file.getFileName();

        if(!("").equals(fileName)){

            System.out.println("Server path:" +filePath);
            File newFile = new File(filePath, fileName);

            if(!newFile.exists()){
                FileOutputStream fos = new FileOutputStream(newFile);
                fos.write(file.getFileData());
                fos.flush();
                fos.close();
                DataManage dm = new DataManage();
                if(userFilePath.startsWith("/")){
                    userFilePath= userFilePath.substring(1,userFilePath.length());
                }
                dm.insertFile(fileName,0,Integer.valueOf(userId),String.valueOf(file.getFileSize()),file.getContentType(),new Date(new java.util.Date().getYear(),new java.util.Date().getMonth(),new java.util.Date().getDate()),userFilePath+"/"+fileName);
            }

            request.setAttribute("uploadedFilePath",newFile.getAbsoluteFile());
            request.setAttribute("uploadedFileName",newFile.getName());
        }



        UserInfo ui = AuthorityUtil.getUser(request);
        if( ui==null ) {
//            addMessage( request, "error.login.first" );
            return mapping.findForward( "manage" );
        }

        UserDAO dao = new UserDAO();
        UserEntity user = dao.getUserById( ui.getUserId());
        dao.checkRank(user);


        FileDirectionary fileDirectionary=new FileDirectionary();
        TreeNode root=fileDirectionary.buildTree(userId);


        request.setAttribute("filePathTestTree",root);
        request.setAttribute( "user", user );
        request.setAttribute("userId",String.valueOf(user.getuId()));
        request.setAttribute("userName",String.valueOf(user.getName()));
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