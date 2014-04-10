package model;

import java.util.HashSet;
import java.util.Set;

//@javax.persistence.Table(name = "User", schema = "", catalog = "461PROJECT")
//@Entity
public class UserEntity {
    public UserEntity(){}
    public UserEntity(int uId, String name, String email, String password, String gender, String birthday, String address, String phone, String createdate, int rank, int role) {
        this.uId = uId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.createdate = createdate;
        this.rank = rank;
        this.role = role;
    }

    private int uId;

//    @javax.persistence.Column(name = "uID")
//    @Id
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    private String name;

//    @javax.persistence.Column(name = "name")
//    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String email;

//    @javax.persistence.Column(name = "email")
//    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String password;

//    @javax.persistence.Column(name = "password")
//    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String gender;

//    @javax.persistence.Column(name = "gender")
//    @Basic
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String birthday;

//    @javax.persistence.Column(name = "birthday")
//    @Basic
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    private String address;

//    @javax.persistence.Column(name = "address")
//    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String phone;

//    @javax.persistence.Column(name = "phone")
//    @Basic
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String createdate;

//    @javax.persistence.Column(name = "createdate")
//    @Basic
    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    private int rank;

//    @javax.persistence.Column(name = "rank")
//    @Basic
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private int role;

//    @javax.persistence.Column(name = "role")
//    @Basic
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    private Set files = new HashSet();


    public Set getFiles() {
        return files;
    }

    public void setFiles(Set files) {
        this.files = files;
    }

    private Set sessions = new HashSet();

    public Set getSessions() {
        return sessions;
    }

    public void setSessions(Set sessions) {
        this.sessions = sessions;
    }

    private Set deletes = new HashSet();

    public Set getDeletes() {
        return deletes;
    }

    public void setDeletes(Set deletes) {
        this.deletes = deletes;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (rank != that.rank) return false;
        if (role != that.role) return false;
        if (uId != that.uId) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (createdate != null ? !createdate.equals(that.createdate) : that.createdate != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
        result = 31 * result + rank;
        result = 31 * result + role;
        return result;
    }



}
