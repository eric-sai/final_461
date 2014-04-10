package historyMachineController;

import UserManagement.hibernate.UserDAO;
import model.FileEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.sql.Date;
import java.util.List;

/**
 * this class is used to get file back from deleted file table
 */
//exchange fid and
public class restore {
    private Session session;
    public restore()
    {
        Configuration configuration=new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session=sessionFactory.openSession();
        session.beginTransaction();
    }
    //delete Delete table and get the Fid
     public List deleteDeleteTable(String fileName)
     {
         Query q = session.createQuery("select distinct new list(F.fid,F.createdate,F.filename,F.shared,F.size,F.type,F.uid) from DeletedFileEntity F where F.filename=?").setString(0,fileName);
         List<List> list_f = q.list();
         session.getTransaction().commit();
         session.delete(list_f.get(0));
         return list_f.get(0);
     }
     public void insertFileTable(List list)
     {
         UserDAO UD = new UserDAO();
         FileEntity f= new FileEntity(Integer.valueOf(list.get(0).toString()),
                 list.get(2).toString(),
                 Integer.valueOf(list.get(3).toString()),
                 UD.getUserById(Integer.valueOf( list.get(6).toString())),
                 list.get(4).toString(),
                 list.get(5).toString(),
                 new Date(new java.util.Date().getYear(),new java.util.Date().getMonth(),new java.util.Date().getDay())) ;
            session.merge(f);
     }



}
