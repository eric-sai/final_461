package model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: readman
 * Date: 11/30/12
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "FileDir", schema = "", catalog = "461PROJECT")
@Entity
public class FileDirEntity {

    public FileDirEntity(){}
    public FileDirEntity(int fid, String directoryTree) {
        this.fid = fid;
        this.directoryTree = directoryTree;
    }

    private int fid;


    @javax.persistence.Column(name = "fid")
    @Id
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    private String directoryTree;

    @javax.persistence.Column(name = "DirectoryTree")
    @Basic
    public String getDirectoryTree() {
        return directoryTree;
    }

    public void setDirectoryTree(String directoryTree) {
        this.directoryTree = directoryTree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileDirEntity that = (FileDirEntity) o;

        if (fid != that.fid) return false;
        if (directoryTree != null ? !directoryTree.equals(that.directoryTree) : that.directoryTree != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fid;
        result = 31 * result + (directoryTree != null ? directoryTree.hashCode() : 0);
        return result;
    }
}
