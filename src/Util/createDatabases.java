package Util;
import java.sql.*;
/**
 * Created with IntelliJ IDEA.
 * User: Readman
 * Date: 10/28/12
 * Time: 2:05 PM
 *
 * This file will create a empty database followed by the design
 * Please note that the new database is named "461PROJECT"
 * Please do not have any duplicated name database.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Please do not change anythings, unless you asked me
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class createDatabases {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();

            String sql = "DROP DATABASE 461PROJECT";
            stmt.executeUpdate(sql);
            // create 461PROJECT
            sql = "CREATE DATABASE 461PROJECT";
            stmt.executeUpdate(sql);
            sql = "USE 461PROJECT"        ;
            stmt.executeUpdate(sql);

            //create tables;
            String User = "create table User (" +
                    "uID INTEGER auto_increment," +
                    "name VARCHAR(255),"+
                    "email VARCHAR(255),"+
                    "password VARCHAR(255),"+
                    "gender VARCHAR(255),"+
                    "birthday VARCHAR(255),"+
                    "address VARCHAR(255),"+
                    "phone VARCHAR(255),"+
                    "createdate VARCHAR(255),"+
                    "rank INTEGER,"+
                    "role INTEGER,"+
                    "Primary Key (uID) "+
                    ")";
            stmt.executeUpdate(User);

            String File = "create table File (" +
                    "fid INTEGER, " +
                    "filename VARCHAR(255),"+
                    "shared INTEGER,"+
                    "uid INTEGER,"+
                    "size VARCHAR(255),"+
                    "type VARCHAR(255),"+
                    "createdate DATE," +
                    "Primary Key (fid)"+
            ")";
            stmt.executeUpdate(File);


            //
            String DeletedFile = "create table DeletedFile (" +
                                "fid INTEGER, " +
                                "shared INTEGER,"+
                                "filename VARCHAR(255),"+
                                "uid INTEGER,"+
                                "size VARCHAR(255),"+
                                "type VARCHAR(255),"+
                                "createdate DATE," +
                                "Primary Key (fid)"+
                        ")";
                        stmt.executeUpdate(DeletedFile);

              //
            String session =  "create table session (" +
                    "fid INTEGER auto_increment,"+
                    "sessionid INTEGER,"+
                    "sessiontime VARCHAR(255)," +
                    "Primary Key (fid)"+
                    ")";
            stmt.executeUpdate(session);

            String FileDir =  "create table FileDir (" +
                    "fid INTEGER,"+
                    "DirectoryTree VARCHAR(255)," +
                    "Primary Key (fid)"+
                    ")";
            stmt.executeUpdate(FileDir);
            stmt.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }//end main

}
