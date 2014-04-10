package UploadDownloadController;

import UserManagement.hibernate.UserDAO;
import model.DeletedFileEntity;
import model.FileDirEntity;
import model.FileEntity;
import model.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


//import javax.imageio.spi.ServiceRegistry;

/**
 * this class is used to delete file. it will move the file from file table to deletedFile table. It will also generate the disk folder with treenode
 */
public class DataManage {

    /**
     * This method is used to recover the file that used deteled
     * @param fid the file id
     * @return true if it success
     */
    private boolean unDeleteFile(int fid, int uid) {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query q = session.createQuery("from DeletedFileEntity f where f.fid = ?").setInteger(0, fid);
        List<DeletedFileEntity> f = q.list();
        if (f.size() != 1) {
            return false;
        }

        //delete data

        System.out.println("undelete data.");

//        Query d = session.createQuery("delete from DeletedFileEntity f where fid = ?").setInteger(0, fid);
//        d.executeUpdate();
        UserDAO UD1 = new UserDAO();
        UserEntity user = UD1.getUserById(uid)  ;
        Set<DeletedFileEntity> dFiles = user.getDeletes();
        Iterator<DeletedFileEntity> itD = dFiles.iterator();
        while(itD.hasNext())
        {
            DeletedFileEntity temp = itD.next();
            if(temp.getFid() == fid)
            {
                session.delete(temp);
                break;
            }
        }

        //insert into delete files.

        int shared = f.get(0).getShared();

        String filename = f.get(0).getFilename();
        String size = f.get(0).getSize();
        String type = f.get(0).getType();

        UserDAO UD = new UserDAO();

        FileEntity fe = new FileEntity(fid,
                filename,
                shared,
                UD.getUserById(uid),
                size,
                type,
                new Date(new java.util.Date().getYear(), new java.util.Date().getMonth(), new java.util.Date().getDate()));
        session.merge(fe);

        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean insertFile(String filename, int shared, int uid, String size, String type, Date createdate, String directoryTree) {


        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //check database content

        Query q = session.createQuery("from FileDirEntity f where f.directoryTree = ?").setString(0, directoryTree);   //check same names with same directory

        List<FileDirEntity> l = q.list();
        //check conflict
        if (l.size() != 0)  //dir in dir table, both folder and file
        {
            //check if is already exist in file table
            Query checkInFileTable = session.createQuery("from FileEntity f where f.fid = ?").setInteger(0, l.get(0).getFid());
            List<FileEntity> checkInFileTableList = checkInFileTable.list();
            if (checkInFileTableList.size() == 0)//in delete file table
            {
                return unDeleteFile(l.get(0).getFid(),uid);
            } else//in file table
            {
                //delete the old ones.
//                Query deleteInFileTable = session.createQuery("delete from FileEntity f where f.fid = ?").setString(0, l.get(0).get(0).toString());
//                deleteInFileTable.executeUpdate();
//                Query deleteInDirTable = session.createQuery("delete from FileDirEntity f where f.fid = ?").setString(0, l.get(0).get(0).toString());
//                deleteInDirTable.executeUpdate();
                Query deleteEntityFile = session.createSQLQuery("from FileDirEntity f where f.fid = ?").setInteger(0, l.get(0).getFid());
                List<FileDirEntity> eD = deleteEntityFile.list();
                for(FileDirEntity f: eD)
                {
                    session.delete(f);
                }



                UserDAO UD = new UserDAO();
                UserEntity user = UD.getUserById(uid);
                Set<FileEntity> files = user.getFiles();
                Iterator<FileEntity> itF = files.iterator();
                while(itF.hasNext())
                {
                    FileEntity file = itF.next();
                    if(file.getFid() == l.get(0).getFid())
                    {
                        session.delete(file);
                        break;
                    }
                }
            }

        }


        //set fid
        Query f = session.createQuery("from FileDirEntity f");
        List<FileDirEntity> fl = f.list();
        int fid;
        if (fl.size() == 0) {
            fid = 0;
        } else {
            fid = fl.get(0).getFid();
            for (int i = 1; i < fl.size(); i++) {
                if (fid < (fl.get(i).getFid() + 1)) {
                    fid = fl.get(i).getFid();
                }
            }

            fid += 1;
        }

        UserDAO UD = new UserDAO();
        FileEntity fe = new FileEntity(fid, filename, shared, UD.getUserById(uid), size, type, createdate);
        session.merge(fe);

        FileDirEntity fde = new FileDirEntity(fid, directoryTree);
        session.merge(fde);

        session.getTransaction().commit();
        session.close();

        return true;
    }

    public boolean deleteFolder(int folderID, int uid) {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        //delete root
        if (folderID == -1) {

            String dir = uid + "";

            Query q1 = session.createQuery("from FileDirEntity f");
            List<FileDirEntity> f1 = q1.list();

            ArrayList<Integer> fids = new ArrayList<Integer>();
            for (int i = 0; i < f1.size(); i++) {
                if (f1.get(i).getDirectoryTree().contains(dir) && !f1.get(i).getDirectoryTree().equals(dir)) {
                    fids.add(f1.get(i).getFid());
                }
            }


            for (int fid : fids) {
                deleteFile(fid, uid);
            }

        }
        //delete subfolder
        else {
            Query q = session.createQuery("from FileDirEntity f where f.fid = ?").setInteger(0, folderID);
            List<FileDirEntity> f = q.list();
            if (f.size() != 1) {
                return false;
            }

            String dir = f.get(0).getDirectoryTree();

            Query q1 = session.createQuery("from FileDirEntity f");
            List<FileDirEntity> f1 = q1.list();

            ArrayList<Integer> fids = new ArrayList<Integer>();
            for (int i = 0; i < f1.size(); i++) {
                if (f1.get(i).getDirectoryTree().contains(dir)) {
                    fids.add(f1.get(i).getFid());
                }
            }

            for (int fid : fids) {
                deleteFile(fid, uid);
            }

        }

        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean shareFolder(int folderID, int uid) {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String dir;
        //get share property.
        int share = 0;
        //root
        if (folderID == -1) {

            dir = uid + "";
        }
        //delete subfolder
        else {
            Query q = session.createQuery("from FileDirEntity f where f.fid = ?").setInteger(0, folderID);
            List<FileDirEntity> f = q.list();
            if (f.size() != 1) {
                return false;
            }

            dir = f.get(0).getDirectoryTree();

        }

        System.out.println(dir);

        Query q1 = session.createQuery("from FileDirEntity f");
        List<FileDirEntity> f1 = q1.list();

        ArrayList<Integer> fids = new ArrayList<Integer>();
        for (int i = 0; i < f1.size(); i++) {
            //contain the same dir
            if (f1.get(i).getDirectoryTree().contains(dir)) {
                fids.add(f1.get(i).getFid());
            }
            //find folder share property
            if (f1.get(i).getDirectoryTree().equals(dir)) {
                Query x = session.createQuery("from FileEntity f where fid = ?").setInteger(0, f1.get(i).getFid());
                List<FileEntity> xL = x.list();
                if (xL.size() == 0) {
                    System.out.println("XL size is zero, fid is " + f1.get(i).getFid());
                    return false;
                }
                if (xL.get(0).getShared() == 0) {
                    share = 1;
                }
            }
        }

        for (int fid : fids) {
            shareFile(fid, share, uid);
        }


        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean deleteFile(int fid, int uid) {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query q = session.createQuery("from FileEntity f where f.fid = ?").setInteger(0, fid);
        List<FileEntity> f = q.list();
        if (f.size() != 1) {
            return false;
        }

        //delete data

        System.out.println("delete data.");
        session.delete(f.get(0));

//        Query d = session.createQuery("delete from FileEntity f where fid = ?").setInteger(0, fid);
//        d.executeUpdate();
//        UserDAO UD2 = new UserDAO();
//        UserEntity user1 = UD2.getUserById(uid);
//        Set<FileEntity> files = user1.getFiles();
//        Iterator<FileEntity> itF = files.iterator();
//        while(itF.hasNext())
//        {
//            FileEntity temp = itF.next();
//            if(temp.getFid() == fid)
//            {
//                session.delete(temp);
//                break;
//            }
//        }


        //insert into delete files.

        int shared = f.get(0).getShared();

        String filename = f.get(0).getFilename();

        String size = f.get(0).getSize();
        String type = f.get(0).getType();
        UserDAO UD = new UserDAO();
        DeletedFileEntity fe = new DeletedFileEntity(fid,
                shared,
                filename,
                UD.getUserById(uid),
                size,
                type,
                new Date(new java.util.Date().getYear(), new java.util.Date().getMonth(), new java.util.Date().getDate()));
        session.merge(fe);


        //check the number of dates in delete file table.

        Query check = session.createQuery("from DeletedFileEntity d");
        List<DeletedFileEntity> checkList = check.list();
        Query checkDate = session.createQuery(("select distinct new list(d.createdate) from DeletedFileEntity  d"))  ;

        //Deleted the oldest files
        System.out.println("checkList Size:"+checkDate.list().size());
        if (checkDate.list().size() >= 5) {

            //find oldest time file
            Date oldestTime = checkList.get(0).getCreatedate();

            for (int i = 1; i < checkList.size(); i++) {

                Date dates = checkList.get(i).getCreatedate();
                System.out.println(dates);

                if (oldestTime.compareTo(dates) > 0) {
                    oldestTime = new Date(dates.getTime());
                }
            }
            //delete files with oldest time
            Query filesNeededToBeDeleted=session.createQuery("select distinct new list(f.directoryTree) from DeletedFileEntity d, FileDirEntity f where f.fid=d.fid and d.createdate=?").setString(0, oldestTime.toString());
            List<List> filePathNeededToBeDeleted=filesNeededToBeDeleted.list();
            String downloadFilesPath = System.getProperty("user.dir")+"/out/artifacts/COMS461_war_exploded/downloadFiles/";
            for(List filePathList:filePathNeededToBeDeleted){
                String fileName=downloadFilesPath+filePathList.get(0);
                File fileNeededToBeDeleted = new File(fileName);
                // Make sure the file or directory exists and isn't write protected
                if (!fileNeededToBeDeleted.exists())
                    continue;
                if (!fileNeededToBeDeleted.canWrite())
                    continue;
                fileNeededToBeDeleted.delete();
            }

            for(DeletedFileEntity d : checkList)
            {
                if(d.getCreatedate().equals(oldestTime))
                {
                    Query findDir = session.createQuery("from FileDirEntity f where f.fid = ?").setInteger(0,d.getFid());
                    List<FileDirEntity> file = findDir.list();
                    session.delete(file.get(0));

                    session.delete(d);
                }
            }
        }

        session.getTransaction().commit();
        session.close();
        return true;
    }

    private Date getDateFromString(String time) {
        String tmp[] = time.split("-");
        int a = Integer.valueOf(tmp[0]);
        Date b = new Date(a - 1900, Integer.valueOf(tmp[1]) - 1, Integer.valueOf(tmp[2]));
        return b;
    }

    public boolean shareFile(int fid, int shareValue, int uid) {

        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query q = session.createQuery("from FileEntity f where f.fid = ?").setInteger(0, fid);
        //if do not have this fid.
        List<FileEntity> f = q.list();
        if (f.size() != 1) {
            return false;
        }


//        Query d = session.createQuery("update FileEntity f set f.shared = ? where fid = ?");//.setInteger(0,isShare?1:0).setInteger(1,fid);
        int shared = 0;
        if (shareValue != -1)//share a total folder, follow the folder's property.
        {
            shared = shareValue;
//            d.setInteger(0, shareValue);
        } else  //only a file.
        {
//            String share = "0";

            if (f.get(0).getShared() == 0) {
//                share = "1";
                shared = 1;
            }
//            d.setString(0, share);
        }
//        d.setInteger(1, fid);
//        d.executeUpdate();
        UserDAO UD = new UserDAO();
        UserEntity user = UD.getUserById(uid);
        Set<FileEntity> files = user.getFiles();
        Iterator<FileEntity> it = files.iterator();
        while(it.hasNext())
        {
            FileEntity file = it.next();
            if(file.getFid() == fid)
            {
                file.setShared(shared);
                break;
            }
        }
        UD.updateUser(user);


        session.getTransaction().commit();
        session.close();
        return true;
    }

}
