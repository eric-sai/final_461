package model;

import UserManagement.hibernate.UserDAO;
import org.hibernate.Session;
import UserManagement.hibernate.HibernateSessionFactory;
import org.hibernate.Transaction;

/**
 * Created with IntelliJ IDEA.
 * User: yuhair
 * Date: 12-12-5
 * Time: 下午6:03
 * To change this template use File | Settings | File Templates.
 */
public class test {
    public static void main(String[] args) {
    Session session =HibernateSessionFactory.getSession();
    Transaction tx=session.beginTransaction();
    String hsql="from UserEntity as u where u.name= 'admina'";
    UserEntity result = (UserEntity)session.createQuery(hsql).uniqueResult();
        SessionEntity test = (SessionEntity)result.getSessions().iterator().next();
        session.delete(test);
    result.getSessions().remove(test)   ;
        UserDAO dao= new UserDAO();

        tx.commit();
        session.close();
        dao.updateUser(result );
    }
}
