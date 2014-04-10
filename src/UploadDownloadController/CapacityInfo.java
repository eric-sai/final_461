package UploadDownloadController;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Scanner;

/**
 * This class is used to calculate the used size of disk
 */
public class CapacityInfo implements ICapacityInfo {
    /**
     * this method is used to get the used size of specific user
     * @param UserId the user
     * @return  the size in int
     */
    public int getUserCapacity(int UserId) {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query q = session.createQuery("select new list(f.size) from FileEntity f where f.uid=?").setInteger(0, UserId);
        List<List> ls = q.list();
        session.getTransaction().commit();
        session.close();

        String temp1 = ls.toString();
        String temp = temp1.substring(1);

        System.out.println(temp);

        Scanner sc = new Scanner(temp);

        int size = 0;
        while(sc.hasNext())
        {
            String sSize = sc.next();
             size += Integer.parseInt(sSize.substring(1,sSize.length()-2));
//            System.out.println("Size:"+size);
        }
//         System.out.println("Size:"+size);
        //TODO: Get user's Rank and check the Capacity.


        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
