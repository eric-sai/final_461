package historyMachineController.Action;

import UserManagement.hibernate.UserDAO;
import model.FileEntity;
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
import java.sql.Date;
import java.util.List;

/**
 * this action can get the file from deleted file table back to file table
 */
public class GetFileBackAction extends Action {

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception{
        //
              String fid = request.getParameter("fid");
        ////
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //select file from delete table by fid
        Query q = session.createQuery("select new list(f.fid, f.shared, f.filename, f.user.uId, f.size, f.type, f.createdate) from DeletedFileEntity f where f.fid = ?").setString(0, fid);
        List<List> f = q.list();
        //get every value form the previous file list
        String shared = f.get(0).get(1).toString();
        String filename = f.get(0).get(2).toString();
        String uid= f.get(0).get(3).toString();
        String size = f.get(0).get(4).toString();
        String type = f.get(0).get(5).toString();

        //delete file from delete table by fid
        Query d = session.createQuery("delete from DeletedFileEntity f where f.fid = ?").setInteger(0,Integer.valueOf( fid));
        d.executeUpdate();

        //insert into delete files.
        //try to output a file name
        System.out.println(f.get(0).get(2));
        // get the fileDirectory from the FileDirectory Table
        Query q1 = session.createQuery("select new list(fd.directoryTree) from FileDirEntity fd where fd.fid = ?").setString(0, fid);
        List<List> dr = q1.list();
        System.out.println(dr.get(0).get(0).toString());
        Query q2 = session.createQuery("select new list(fd.fid) from FileDirEntity fd where fd.directoryTree = ?").setString(0, dr.get(0).get(0).toString());
        List<List> dr1 = q2.list();

        //   System.out.println("size: "+dr1.size());
        if(dr1.size()!=0)
        {
            for(List list:dr1)
            {
                //     System.out.println("test"+list.get(0).toString());
                //    System.out.println("test"+uid);
                // get the file table's file and put into the delete table;
                /*           Query Fil = session.createQuery("select new list(f.fid, f.shared, f.filename, f.uid, f.size, f.type, f.createdate) from FileEntity f where f.fid = ?").setString(0, list.get(0).toString());
               List<List> fileList = Fil.list();
                //get every value form the previous file list
                String shared1 = fileList.get(0).get(1).toString();
                String filename1 = fileList.get(0).get(2).toString();
                String uid1= fileList.get(0).get(3).toString();
                String size1 = fileList.get(0).get(4).toString();
                String type1 = fileList.get(0).get(5).toString();

               DeletedFileEntity deletefILE= new DeletedFileEntity(Integer.valueOf( list.get(0).toString()), Integer.valueOf(shared1), filename1, Integer.valueOf(uid1),  size1,type1,  new Date(new java.util.Date().getYear(), new java.util.Date().getMonth(), new java.util.Date().getDate()));
                session.merge(deletefILE);
                */
                //delete the file from file table according to the fid, filename, size, shared, uid.
                Query df= session.createQuery("delete from FileEntity  F where F.fid = ? and F.user.uId=? ").setString(0,list.get(0).toString()).setString(1,uid);

                df.executeUpdate();
            }
        }
        //input the information of the file from the delete table in to the file table
        UserDAO UD = new UserDAO();
        FileEntity fe = new FileEntity(Integer.valueOf(fid),
                filename,
                Integer.parseInt(shared),
                UD.getUserById(Integer.parseInt(uid)),
                size,
                type,
                new Date(new java.util.Date().getYear(), new java.util.Date().getMonth(), new java.util.Date().getDate()));
        session.merge(fe);

        session.getTransaction().commit();
        session.close();
        return mapping.findForward("success");

    }

}
