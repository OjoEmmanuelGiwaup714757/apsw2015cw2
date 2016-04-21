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
public class Shortlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;//1--not allocated 2--allocated
    private Date submissiondate;
    @ManyToOne
    private Project ideapicked;
    @ManyToOne
    private Registation supervise;
    @ManyToOne
    private Registation moderate;
    @ManyToOne
    private Registation study;
    @OneToMany(mappedBy="finalproject")
    private List<Milestones> mymilestone;//final project idea milestone

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSubmissiondate() {
        return submissiondate;
    }

    public void setSubmissiondate(Date submissiondate) {
        this.submissiondate = submissiondate;
    }



    public Project getIdeapicked() {
        return ideapicked;
    }

    public void setIdeapicked(Project ideapicked) {
        this.ideapicked = ideapicked;
    }

    public Registation getSupervise() {
        return supervise;
    }

    public void setSupervise(Registation supervise) {
        this.supervise = supervise;
    }

    public Registation getModerate() {
        return moderate;
    }

    public void setModerate(Registation moderate) {
        this.moderate = moderate;
    }

    public Registation getStudy() {
        return study;
    }

    public void setStudy(Registation study) {
        this.study = study;
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
        if (!(object instanceof Shortlist)) {
            return false;
        }
        Shortlist other = (Shortlist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ents.Shortlist[ id=" + id + " ]";
    }

}
