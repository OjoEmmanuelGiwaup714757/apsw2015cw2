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
public class Milestones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String filepath;
    private String comment;
    private Date submissiondate;

    @ManyToOne
    private Milestonetemplate belongtemplate;

    @ManyToOne
    private Registation milestoneowner;

    @OneToMany(mappedBy = "belongfeedback")
    private List<Milestonefeedback> myfeedback;

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
        if (!(object instanceof Milestones)) {
            return false;
        }
        Milestones other = (Milestones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ents.Milestones[ id=" + id + " ]";
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getSubmissiondate() {
        return submissiondate;
    }

    public void setSubmissiondate(Date submissiondate) {
        this.submissiondate = submissiondate;
    }

    public Registation getMilestoneowner() {
        return milestoneowner;
    }

    public void setMilestoneowner(Registation milestoneowner) {
        this.milestoneowner = milestoneowner;
    }

    public Milestonetemplate getBelongtemplate() {
        return belongtemplate;
    }

    public void setBelongtemplate(Milestonetemplate belongtemplate) {
        this.belongtemplate = belongtemplate;
    }

}
