/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ents;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gooloo
 */
@Entity
@Table(name = "MILESTONE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Milestone.findAll", query = "SELECT m FROM Milestone m"),
    @NamedQuery(name = "Milestone.findByMilestoneid", query = "SELECT m FROM Milestone m WHERE m.milestoneid = :milestoneid"),
    @NamedQuery(name = "Milestone.findByMilestonetemplateid", query = "SELECT m FROM Milestone m WHERE m.milestonetemplateid = :milestonetemplateid"),
    @NamedQuery(name = "Milestone.findBySubmissiondate", query = "SELECT m FROM Milestone m WHERE m.submissiondate = :submissiondate"),
    @NamedQuery(name = "Milestone.findByStudentid", query = "SELECT m FROM Milestone m WHERE m.studentid = :studentid")})
public class Milestones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MILESTONEID")
    private Integer milestoneid;
    @Lob
    @Size(max = 32700)
    @Column(name = "SUBMISSIONCOMMENTS")
    private String submissioncomments;
    @Lob
    @Column(name = "FILEPART")
    private String filepart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MILESTONETEMPLATEID")
    private int milestonetemplateid;
    @Column(name = "SUBMISSIONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissiondate;
    @Size(max = 255)
    @Column(name = "STUDENTID")
    private String studentid;

    public Milestones() {
    }

    public Milestones(Integer milestoneid) {
        this.milestoneid = milestoneid;
    }

    public Milestones(Integer milestoneid, int milestonetemplateid) {
        this.milestoneid = milestoneid;
        this.milestonetemplateid = milestonetemplateid;
    }

    public Integer getMilestoneid() {
        return milestoneid;
    }

    public void setMilestoneid(Integer milestoneid) {
        this.milestoneid = milestoneid;
    }

    public String getSubmissioncomments() {
        return submissioncomments;
    }

    public void setSubmissioncomments(String submissioncomments) {
        this.submissioncomments = submissioncomments;
    }

    public String getFilepart() {
        return filepart;
    }

    public void setFilepart(String filepart) {
        this.filepart = filepart;
    }

    public int getMilestonetemplateid() {
        return milestonetemplateid;
    }

    public void setMilestonetemplateid(int milestonetemplateid) {
        this.milestonetemplateid = milestonetemplateid;
    }

    public Date getSubmissiondate() {
        return submissiondate;
    }

    public void setSubmissiondate(Date submissiondate) {
        this.submissiondate = submissiondate;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (milestoneid != null ? milestoneid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Milestones)) {
            return false;
        }
        Milestones other = (Milestones) object;
        if ((this.milestoneid == null && other.milestoneid != null) || (this.milestoneid != null && !this.milestoneid.equals(other.milestoneid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ents.Milestone[ milestoneid=" + milestoneid + " ]";
    }

    public void setFilepart(File afile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
