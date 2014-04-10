package historyMachineController;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * this class is used to insert directory tree into file table
 */
public class InsertTable {
    public Session session;

    public InsertTable()
    {

        Configuration configuration=new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session=sessionFactory.openSession();
        session.beginTransaction();
    }


    public  void insert( String parentFolder,String fileName)  {


    }
    public List<List> directoryTreeselect(String uid)
    {

        Query q = session.createQuery("select distinct new list(Fd.directoryTree) from FileDirEntity Fd , FileEntity F where Fd.fid=F.fid and F.user.uId=?").setString(0,uid);

        //check the fid in fileDir and the fid from file table

        List<List> list_f = q.list();
        session.getTransaction().commit();
         return list_f;
    }

}
