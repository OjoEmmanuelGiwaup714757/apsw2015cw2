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
public class Milestonetemplate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String mttitle;
    private String mtdesc;
    private Long mtstatus;//1--not available 2--available. decided by activedate and duedate
    private Date mtactivedate;
    private Date mtduedate;
    private Long mtauthority;
    
    @OneToMany(mappedBy="templateassign")
    private List<Cohorts> templatepicker;
    
    @OneToMany(mappedBy="belongtemplate")
    private List<Milestones> mymilestone;

    @ManyToOne
    private Registation milestonetemplateowner;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Milestonetemplate)) {
            return false;
        }
        Milestonetemplate other = (Milestonetemplate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ents.Milestonetemplate[ id=" + id + " ]";
    }

    public String getMttitle() {
        return mttitle;
    }

    public void setMttitle(String mttitle) {
        this.mttitle = mttitle;
    }

    public String getMtdesc() {
        return mtdesc;
    }

    public void setMtdesc(String mtdesc) {
        this.mtdesc = mtdesc;
    }

    public Long getMtstatus() {
        return mtstatus;
    }

    public void setMtstatus(Long mtstatus) {
        this.mtstatus = mtstatus;
    }

    public Date getMtactivedate() {
        return mtactivedate;
    }

    public void setMtactivedate(Date mtactivedate) {
        this.mtactivedate = mtactivedate;
    }

    public Date getMtduedate() {
        return mtduedate;
    }

    public void setMtduedate(Date mtduedate) {
        this.mtduedate = mtduedate;
    }

    public Long getMtauthority() {
        return mtauthority;
    }

    public void setMtauthority(Long mtauthority) {
        this.mtauthority = mtauthority;
    }
    
    
    
    

}
