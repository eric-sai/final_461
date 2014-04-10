package model;

//@javax.persistence.Table(name = "session", schema = "", catalog = "461PROJECT")
//@Entity
public class SessionEntity {
    private int fid;
    public  SessionEntity(){}

    public SessionEntity(int fid, UserEntity user, String sessiontime) {
        this.fid = fid;
        this.user = user;
        this.sessiontime = sessiontime;
    }

//    public SessionEntity(int fid, int sessionid, String sessiontime) {
//        this.fid = fid;
//        this.sessionid = sessionid ;
//        this.sessiontime = sessiontime;
//    }
//    @javax.persistence.Column(name = "fid")
//    @Id
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

//    private int sessionid;
//
//    public int getSessionid() {
//        return sessionid;
//    }
//
//    public void setSessionid(int sessionid) {
//        this.sessionid = sessionid;
//    }


//    @javax.persistence.Column(name = "sessionid")
//    @Basic


    private String sessiontime;

//    @javax.persistence.Column(name = "sessiontime")
//    @Basic
    public String getSessiontime() {
        return sessiontime;
    }

    public void setSessiontime(String sessiontime) {
        this.sessiontime = sessiontime;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        SessionEntity that = (SessionEntity) o;
//
//        if (fid != that.fid) return false;
//        if (sessionid != that.sessionid) return false;
//        if (sessiontime != null ? !sessiontime.equals(that.sessiontime) : that.sessiontime != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = fid;
//        result = 31 * result + sessionid;
//        result = 31 * result + (sessiontime != null ? sessiontime.hashCode() : 0);
//        return result;
//    }

}
