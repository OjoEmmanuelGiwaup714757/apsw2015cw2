/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ents;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author up792072
 */
@Entity
public class Registation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String studentId;

    private String firstName;
    private String middlename;
    private String surname;
    private String phoneno;
    private String emailaddr;
    private String password;
    private String username;

    private Long iscoordinator;//1--not coordinator 2--is coordinator
    private Long isSupervisor;//1--not Supervisor 2--is Supervisor
    private Long isModerator;//1--not Moderator 2--is Moderator
    private Long personstatusid;//1--not allocated 2--allocated
    private Long registrationstatus;//1--not confirmed 2--confirmed through email
    private Long studentcategoryid; //1--first time,not repeation 2--repeation
    private Long suspensionstatus;//1--not suspensin 2--suspension
    private Date accountcreateddate;
    private Date accountconfirmeddate;
    private Long userstatus;

    @OneToMany(mappedBy = "supervise")
    private List<Shortlist> supervisors;
    @OneToMany(mappedBy = "moderate")
    private List<Shortlist> moderators;
    @OneToMany(mappedBy = "study")
    private List<Shortlist> students;
    @ManyToOne
    private Organisation belongOrg;
    @ManyToOne
    private Department belongDepart;
    @OneToMany(mappedBy = "ideaowner")
    private List<Project> myideas;
    @ManyToOne
    private Cohorts belongCoh;

//    @OneToMany(mappedBy = "milestoneowner")
//    private List<Milestones> mymilestones;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getRegistrationstatus() {
        return registrationstatus;
    }

    public void setRegistrationstatus(Long registrationstatus) {
        this.registrationstatus = registrationstatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmailaddr() {
        return emailaddr;
    }

    public void setEmailaddr(String emailaddr) {
        this.emailaddr = emailaddr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getIsSupervisor() {
        return isSupervisor;
    }

    public void setIsSupervisor(Long isSupervisor) {
        this.isSupervisor = isSupervisor;
    }

    public Long getIsModerator() {
        return isModerator;
    }

    public void setIsModerator(Long isModerator) {
        this.isModerator = isModerator;
    }

    public Long getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Long userstatus) {
        this.userstatus = userstatus;
    }

    public Long getPersonstatusid() {
        return personstatusid;
    }

    public void setPersonstatusid(Long personstatusid) {
        this.personstatusid = personstatusid;
    }

    public Long getIscoordinator() {
        return iscoordinator;
    }

    public void setIscoordinator(Long iscoordinator) {
        this.iscoordinator = iscoordinator;
    }

    public Date getAccountcreateddate() {
        return accountcreateddate;
    }

    public void setAccountcreateddate(Date accountcreateddate) {
        this.accountcreateddate = accountcreateddate;
    }

    public Date getAccountconfirmeddate() {
        return accountconfirmeddate;
    }

    public void setAccountconfirmeddate(Date accountconfirmeddate) {
        this.accountconfirmeddate = accountconfirmeddate;
    }

    public List<Shortlist> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(List<Shortlist> supervisors) {
        this.supervisors = supervisors;
    }

    public List<Shortlist> getModerators() {
        return moderators;
    }

    public void setModerators(List<Shortlist> moderators) {
        this.moderators = moderators;
    }

    public List<Shortlist> getStudents() {
        return students;
    }

    public void setStudents(List<Shortlist> students) {
        this.students = students;
    }

    public Organisation getBelongOrg() {
        return belongOrg;
    }

    public void setBelongOrg(Organisation belongOrg) {
        this.belongOrg = belongOrg;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getSurname();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registation)) {
            return false;
        }
        Registation other = (Registation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getFullName();
    }

}
