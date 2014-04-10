package model;

import javax.persistence.Basic;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: readman
 * Date: 11/30/12
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
//@javax.persistence.Table(name = "DeletedFile", schema = "", catalog = "461PROJECT")
//@Entity

public class DeletedFileEntity {
    public DeletedFileEntity(){}

    public DeletedFileEntity(int fid, int shared, String filename, UserEntity user, String size, String type, Date createdate) {
        this.fid = fid;
        this.shared = shared;
        this.filename = filename;
        this.user = user;
        this.size = size;
        this.type = type;
        this.createdate = createdate;
    }

    private int fid;



//    @javax.persistence.Column(name = "fid")
//    @Id
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    private int shared;

//    @javax.persistence.Column(name = "shared")
//    @Basic
    public int getShared() {
        return shared;
    }

    public void setShared(int shared) {
        this.shared = shared;
    }

    private String filename;

//    @javax.persistence.Column(name = "filename")
//    @Basic
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

//    private int uid;
//
//    @javax.persistence.Column(name = "uid")
//    @Basic
//    public int getUid() {
//        return uid;
//    }
//
//    public void setUid(int uid) {
//        this.uid = uid;
//    }

    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }




    private String size;

//    @javax.persistence.Column(name = "size")
//    @Basic
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    private String type;

//    @javax.persistence.Column(name = "type")
//    @Basic
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private Date createdate;

    @javax.persistence.Column(name = "createdate")
    @Basic
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        DeletedFileEntity that = (DeletedFileEntity) o;
//
//        if (fid != that.fid) return false;
//        if (shared != that.shared) return false;
//        if (uid != that.uid) return false;
//        if (createdate != null ? !createdate.equals(that.createdate) : that.createdate != null) return false;
//        if (filename != null ? !filename.equals(that.filename) : that.filename != null) return false;
//        if (size != null ? !size.equals(that.size) : that.size != null) return false;
//        if (type != null ? !type.equals(that.type) : that.type != null) return false;
//
//        return true;
//    }

//    @Override
//    public int hashCode() {
//        int result = fid;
//        result = 31 * result + shared;
//        result = 31 * result + (filename != null ? filename.hashCode() : 0);
//        result = 31 * result + uid;
//        result = 31 * result + (size != null ? size.hashCode() : 0);
//        result = 31 * result + (type != null ? type.hashCode() : 0);
//        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
//        return result;
//    }


}
