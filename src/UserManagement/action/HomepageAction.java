//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package UserManagement.action;

import UploadDownloadController.DataManage;
import UploadDownloadController.Helper.TreeNode;
import UserManagement.hibernate.UserDAO;
import UserManagement.session.AuthorityUtil;
import UserManagement.session.UserInfo;
import historyMachineController.FileDirectionary;
import model.UserEntity;
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
import java.io.File;
import java.sql.Date;
import java.util.List;


/**
 *   this class is to transmit information required by the homepage to the homepage
 */
public class HomepageAction extends BaseAction {


	/** 
	 * Method execute
     * this method is to transmit information required by the homepage to the homepage
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward      forward to the page of homepage
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) throws Exception {


//        response.setHeader("Cache-Control","no-cache");
//        response.setHeader("Cache-Control","no-store");
//        response.setDateHeader("Expires", 0);
//        response.setHeader("Pragma","no-cache");


        UserInfo ui = AuthorityUtil.getUser(request);
        if( ui==null ) {
            addMessage( request, "error.login.first" );
            return mapping.findForward( "manage" );
        }
		
		UserDAO dao = new UserDAO();
		UserEntity user = dao.getUserById( ui.getUserId());
        dao.checkRank(user);

        //initialize the user root file;
        String realFilepath=getServlet().getServletContext().getRealPath("/");
        realFilepath=realFilepath.substring(0,realFilepath.indexOf("branches")+9)+"web";
        File folder = new File(realFilepath+"/downloadFiles/"+String.valueOf(user.getuId()));
        System.out.println("finalTest:  "+folder.getPath());
        if(!folder.exists()){
            folder.mkdirs();
            DataManage dataManage=new DataManage();
            dataManage.insertFile("",0,user.getuId(),"0","type",new Date(new java.util.Date().getYear(),new java.util.Date().getMonth(),new java.util.Date().getDate()),String.valueOf(user.getuId()));
        }


        FileDirectionary fileDirectionary=new FileDirectionary();
        TreeNode root=fileDirectionary.buildTree(String.valueOf(user.getuId()));

        request.setAttribute("filePathTestTree",root);
		request.setAttribute( "user", user );
        request.setAttribute("userId", String.valueOf(user.getuId()));
        request.setAttribute("userName", String.valueOf(user.getName()));

        /*Share File*/
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

		
		return mapping.findForward( "homepage" );
	}

}

