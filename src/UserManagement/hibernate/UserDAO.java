package UserManagement.hibernate;


import UserManagement.exception.UserDAOException;
import model.SessionEntity;
import model.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * this is the user database controller file, which can save user, delete user, search user and get all users
 */
public class UserDAO {
    Session session;
    Transaction tx;

    /**
     * this method can save user
     * @param user the user
     */
	public void saveUser(UserEntity user)
	{
		if (user == null)
			return;
		UserEntity u = getUser(user.getName());
		if (u != null)
			throw new UserDAOException("user duplicates");
		else
            save(user);
	}

    /**
     * this method will check duplicate of users
     * @param user the user
     */
    public void integerCheck(UserEntity user)
    {
        if (user == null)
            return;
        UserEntity u = getUser(user.getName());
        if (u != null)
            throw new UserDAOException("user duplicates");
        else
            save(user);
    }

    /**
     * this method will get the user by user name
     * @param username  the username
     * @return  the user entity
     */
	public UserEntity getUser(String username)
	{   session = HibernateSessionFactory.getSession();
        String hsql="from UserEntity as u where u.name='" + username +"'";
        tx = session.beginTransaction();
        UserEntity result = (UserEntity)session.createQuery(hsql).uniqueResult();
        tx.commit();
        session.close();
        return result;
	}

    /**
     * this method will get user by userid
     * @param id the user id
     * @return  the user enetiy
     */
    public UserEntity getUserById(int id)
    {
        session = HibernateSessionFactory.getSession();
        String hsql="from UserEntity as u where u.uId=" + id;
        tx = session.beginTransaction();
        UserEntity result = (UserEntity)session.createQuery(hsql).uniqueResult();
        tx.commit();
        session.close();
        return result;
    }


   public UserEntity getUserByName(String name)
    {
        session = HibernateSessionFactory.getSession();
        String hsql="from UserEntity as u where u.name='" + name+"'";
        tx = session.beginTransaction();
        UserEntity result = (UserEntity)session.createQuery(hsql).uniqueResult();
        tx.commit();
        session.close();
        return result;
    }



    public Boolean getBytime(String time, int uid)

    {
        session = HibernateSessionFactory.getSession();
        String hsql="from UserEntity as u where u.uId="+ uid;
        tx = session.beginTransaction();
        UserEntity result = (UserEntity)session.createQuery(hsql).uniqueResult();
        tx.commit();
        Set<SessionEntity> set=result.getSessions();
        Iterator ite = set.iterator();
        while (ite.hasNext())
        {
           SessionEntity se= (SessionEntity) ite.next();
           if(se.getSessiontime().equals(time)) {
              return true;
           }

        }
        session.close();
        return false;
    }
    /**
     * this method will update user info
     * @param user the user entity
     */
    public void save(UserEntity user) {
        session = HibernateSessionFactory.getSession();
        tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
        }

    /**
     * this method will update user info
     * @param user the user entity
     */
    public void updateUser(UserEntity user) {
        session = HibernateSessionFactory.getSession();
        tx = session.beginTransaction();
        session.update(user);
        tx.commit();
        session.close();
    }

    /**
     * this method will save user when user login
     * @param userlogin the session entity of user
     */
    public void save(SessionEntity userlogin) {
        session = HibernateSessionFactory.getSession();
        tx = session.beginTransaction();
        session.save(userlogin);
        tx.commit();
        session.close();
    }
    /**
     * this method will update user info
     * @param user the user entity
     */
    public void update(UserEntity user) {
        session = HibernateSessionFactory.getSession();
        tx = session.beginTransaction();
        session.update(user);
        tx.commit();
        session.close();
    }

    /**
     * this method will get all users
     * @return  the list of user
     */
    public List allUsers() {
        session = HibernateSessionFactory.getSession();
        String hql="from UserEntity ";
        tx = session.beginTransaction();
        List<UserEntity> list=session.createQuery(hql).list();
        tx.commit();
        session.close();
        return list;
    }

    /**
     * this method will delete a user by user id
     * @param id  the user id
     */
    public void deleteUser(int id) {
        session = HibernateSessionFactory.getSession();
        String hql="delete UserEntity where uId="+id;
        tx = session.beginTransaction();
        session.createQuery(hql).executeUpdate();
        tx.commit();
        session.close();

    }

    /**
     * this method will check user's rank
     * @param user the user
     */
    public void checkRank(UserEntity user) {
        //session = HibernateSessionFactory.getSession();
        //tx = session.beginTransaction();
       // int uid=user.getuId();
       // String hql="select count(*) as count from SessionEntity as s where s.sessionid="+uid;

       // int count=((Number)session.createQuery(hql).iterate().next()).intValue();
       // tx.commit();
      //  session.close();
        int[] rank={3,1,7,2,15,3,30,4,60,5,110,6,180,7,300,8,450,9,600,10};
        int count=user.getSessions().size();
        for(int i=0;i<rank.length;i++)
        {
           if(rank[i]==count && i%2==0)
           {
               user.setRank(rank[i+1]);
               update(user);
           }
        }
    }

    /**
     * this method will count how many days user login
     * @param uid the user id
     * @return the number of days
     */
    public int countDays(int uid) {
//        session = HibernateSessionFactory.getSession();
//        String hql="select count(*) as count from SessionEntity as s where s.sessionid="+uid;
//        tx = session.beginTransaction();
//        int count=((Number)session.createQuery(hql).iterate().next()).intValue();
//        tx.commit();
//        session.close();
        session = HibernateSessionFactory.getSession();
        String hql=" from UserEntity as u where u.uId="+uid;
        tx = session.beginTransaction();
        UserEntity result = (UserEntity)session.createQuery(hql).uniqueResult() ;
        int count=result.getSessions().size();
        tx.commit();
        session.close();
        return count;
    }

    /**
     * this method will search user by user's info
     * @param name the name
     * @param gender    the gender
     * @param birthday            the birthday
     * @param email the email
     * @param address the address
     * @param phone the phone number
     * @param role  the role
     * @param rank  the rank
     * @return  the list of user
     */
    public List advancedSearch(String name, String gender, String birthday, String email, String address,
                               String phone, String role, String rank) {
        String hql="from UserEntity as u where u.uId<>0 and ";
        if(!name.equals("")){
            hql=hql+"u.name='"+name+"' and ";
        }
        if(!gender.equals("")){
            hql=hql+"u.gender='"+gender+"' and ";
        }
        if(!birthday.equals("0000-00-00")){
            hql=hql+"u.birthday='"+birthday+"' and ";
        }
        if(!email.equals("")){
            hql=hql+"u.email='"+email+"' and ";
        }
        if(!address.equals("")){
            hql=hql+"u.address like '%"+address+"%' and ";
        }
        if(!phone.equals("")){
            hql=hql+"u.phone='"+phone+"' and ";
        }
        if(!role.equals("")){
            int roles=Integer.parseInt(role);
            hql=hql+"u.role="+roles+" and ";
        }
        if(!rank.equals("")){
            int ranks=Integer.parseInt(rank);
            hql=hql+"u.rank="+ranks+" and ";
        }
        hql=hql+"u.uId<>0";
        session = HibernateSessionFactory.getSession();
        tx = session.beginTransaction();
        List<UserEntity> list=session.createQuery(hql).list();
        tx.commit();
        session.close();
        return list;
    }


}
