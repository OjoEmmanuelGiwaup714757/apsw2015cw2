/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ents;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gooloo
 */
@Entity
@Table(name = "MILESTONETEMPLATE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Milestonetemplate.findAll", query = "SELECT m FROM Milestonetemplate m"),
    @NamedQuery(name = "Milestonetemplate.findByMilestonetemplateid", query = "SELECT m FROM Milestonetemplate m WHERE m.milestonetemplateid = :milestonetemplateid"),
    @NamedQuery(name = "Milestonetemplate.findByMilestonetemplateduedate", query = "SELECT m FROM Milestonetemplate m WHERE m.milestonetemplateduedate = :milestonetemplateduedate"),
    @NamedQuery(name = "Milestonetemplate.findByMilestonetemplateactivedate", query = "SELECT m FROM Milestonetemplate m WHERE m.milestonetemplateactivedate = :milestonetemplateactivedate"),
    @NamedQuery(name = "Milestonetemplate.findByMilestonetemplatetitle", query = "SELECT m FROM Milestonetemplate m WHERE m.milestonetemplatetitle = :milestonetemplatetitle"),
    @NamedQuery(name = "Milestonetemplate.findByMilestonetemplategroupstudents", query = "SELECT m FROM Milestonetemplate m WHERE m.milestonetemplategroupstudents = :milestonetemplategroupstudents")})
public class Milestonetemplate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MILESTONETEMPLATEID")
    private Integer milestonetemplateid;
    @Lob
    @Column(name = "MILESTONETEMPLATEDESC")
    private String milestonetemplatedesc;
    @Size(max = 10)
    @Column(name = "MILESTONETEMPLATEDUEDATE")
    private String milestonetemplateduedate;
    @Size(max = 10)
    @Column(name = "MILESTONETEMPLATEACTIVEDATE")
    private String milestonetemplateactivedate;
    @Size(max = 255)
    @Column(name = "MILESTONETEMPLATETITLE")
    private String milestonetemplatetitle;
    @Size(max = 50)
    @Column(name = "MILESTONETEMPLATEGROUPSTUDENTS")
    private String milestonetemplategroupstudents;

    public Milestonetemplate() {
    }

    public Milestonetemplate(Integer milestonetemplateid) {
        this.milestonetemplateid = milestonetemplateid;
    }

    public Integer getMilestonetemplateid() {
        return milestonetemplateid;
    }

    public void setMilestonetemplateid(Integer milestonetemplateid) {
        this.milestonetemplateid = milestonetemplateid;
    }

    public String getMilestonetemplatedesc() {
        return milestonetemplatedesc;
    }

    public void setMilestonetemplatedesc(String milestonetemplatedesc) {
        this.milestonetemplatedesc = milestonetemplatedesc;
    }

    public String getMilestonetemplateduedate() {
        return milestonetemplateduedate;
    }

    public void setMilestonetemplateduedate(String milestonetemplateduedate) {
        this.milestonetemplateduedate = milestonetemplateduedate;
    }

    public String getMilestonetemplateactivedate() {
        return milestonetemplateactivedate;
    }

    public void setMilestonetemplateactivedate(String milestonetemplateactivedate) {
        this.milestonetemplateactivedate = milestonetemplateactivedate;
    }

    public String getMilestonetemplatetitle() {
        return milestonetemplatetitle;
    }

    public void setMilestonetemplatetitle(String milestonetemplatetitle) {
        this.milestonetemplatetitle = milestonetemplatetitle;
    }

    public String getMilestonetemplategroupstudents() {
        return milestonetemplategroupstudents;
    }

    public void setMilestonetemplategroupstudents(String milestonetemplategroupstudents) {
        this.milestonetemplategroupstudents = milestonetemplategroupstudents;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (milestonetemplateid != null ? milestonetemplateid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Milestonetemplate)) {
            return false;
        }
        Milestonetemplate other = (Milestonetemplate) object;
        if ((this.milestonetemplateid == null && other.milestonetemplateid != null) || (this.milestonetemplateid != null && !this.milestonetemplateid.equals(other.milestonetemplateid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ents.Milestonetemplate[ milestonetemplateid=" + milestonetemplateid + " ]";
    }
    
}
