package historyMachineController;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * this class is used to store the file info
*/
public class FileInfoGet {
    public Session session;
    public FileInfoGet()
        {

            Configuration configuration=new Configuration().configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            session=sessionFactory.openSession();
            session.beginTransaction();
        }
    public String getFilID(String dir,String uid)
    {
        System.out.println("in getFileID: "+dir);
        Query q = session.createQuery("select distinct new list(F.fid) from FileDirEntity F, FileEntity  F1 where F.directoryTree=? and F1.user.uId=?").setString(0,dir).setString(1,uid);
        List<List> list_f = q.list();
        session.getTransaction().commit();
        System.out.println("in getFileID, Id output : "+list_f.get(0).get(0).toString());
       return list_f.get(0).get(0).toString();
    }
    public String getFileName(String fid, String uid)
    {
        Query q = session.createQuery("select distinct new list(F.filename) from FileEntity F where F.fid =? and F.user.uId = ? ").setString(0,fid).setString(1,uid);
        List<List> list_f = q.list();
        session.getTransaction().commit();

        return list_f.get(0).get(0).toString();
    }
    public String getFileSize(String fid, String uid)
    {
        Query q = session.createQuery("select distinct new list(F.size) from FileEntity F where F.fid =?and F.user.uId= ?").setString(0,fid).setString(1,uid);
        List<List> list_f = q.list();
        session.getTransaction().commit();
        return list_f.get(0).get(0).toString();
    }
    public String getFileCreateDate(String fid,String uid)
    {
        Query q = session.createQuery("select distinct new list(F.createdate) from FileEntity F where F.fid =? and F.user.uId= ?").setString(0,fid).setString(1,uid);
        List<List> list_f = q.list();
        session.getTransaction().commit();
        return list_f.get(0).get(0).toString();
    }
    public boolean getFileSizeShared(String fid, String uid)
    {
        Query q = session.createQuery("select distinct new list(F.shared) from FileEntity F where F.fid =? and F.user.uId= ?").setString(0,fid).setString(1,uid);
        List<List> list_f = q.list();
        session.getTransaction().commit();
        if(list_f.get(0).get(0).toString().equals("0"))
            return false;
        //if(list_f.get(0).get(0).toString()=="0")
        else
        return true;
    }

    public String getFileType(String fid, String uid)
    {
        Query q = session.createQuery("select distinct new list(F.type) from FileEntity F where F.fid =?").setString(0,fid);
        List<List> list_f = q.list();
        session.getTransaction().commit();
        return list_f.get(0).get(0).toString();
    }
    //public String getFolderId(String fid, String uid)
    //{
     //   Query q = session.createQuery("select distinct new list(F.fid) from FileEntity F where F.fid =?").setString(0,fid);
    //}

}