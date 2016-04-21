/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import ents.Project;
import ents.Registation;
import ents.Shortlist;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pers.ShortlistFacade;
import pers.ProjectFacade;
import pers.RegistationFacade;

/**
 *
 * @author Administrator
 */
@Stateless
public class PAllocationService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private ShortlistFacade slf;
    @EJB
    private ProjectFacade pif;
    @EJB
    private RegistationFacade rf;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Shortlist addnewideaschoice(Shortlist isl) {
        //verify that the operation is valid
        //do operation
        Project pis = isl.getIdeapicked();
        Registation supervisor = isl.getSupervise();
        Registation moderator = isl.getModerate();
        if (pis != null & supervisor != null & moderator != null) {
            pis = pif.edit(pis);
            supervisor = rf.edit(supervisor);
            moderator = rf.edit(moderator);
            isl.setIdeapicked(pis);
            isl.setSupervise(supervisor);
            isl.setModerate(moderator);
            pis.getIdeaspool().add(isl);
            supervisor.getSupervisors().add(isl);
            moderator.getModerators().add(isl);
        }
        slf.create(isl);
        //return an appropriate object
        return isl;
    }

    //add new ideas choice by coordinator
    public Shortlist addnewideaschoicebycoordinator(Shortlist isl) {
        //do operation
        Project pis = isl.getIdeapicked();
        Registation supervisor = isl.getSupervise();
        Registation moderator = isl.getModerate();
        Registation student = isl.getStudy();
        if (pis != null & supervisor != null & moderator != null & student != null) {
            pis = pif.edit(pis);
            supervisor = rf.edit(supervisor);
            moderator = rf.edit(moderator);
            student = rf.edit(student);
            isl.setIdeapicked(pis);
            isl.setSupervise(supervisor);
            isl.setModerate(moderator);
            isl.setStudy(student);
            pis.getIdeaspool().add(isl);
            supervisor.getSupervisors().add(isl);
            moderator.getModerators().add(isl);
            student.getStudents().add(isl);
        }
        slf.create(isl);
        //return an appropriate object
        return isl;
    }

    public List<Shortlist> getAllShortlist() {
        //return slf.findShortlistAvailable();
        return slf.findAll();
    }
        public List<Shortlist> findShortlistBystudent(long userid) {
        return slf.findShortlistBystudent(userid);
    }

    public void allocateProjectChoice(Shortlist sl) {
        rf.allocateStudent(sl.getStudy());
        pif.allocateProjectChoice(sl.getIdeapicked());
        slf.allocateChoice(sl);
    }
    


    public List<Shortlist> getAllShortlistAvailable() {
        return slf.findAll();
    }

    public List<Shortlist> getAllShortlistbystudent() {
        return slf.findAll();
    }

    public List<Project> getAllProject() {
        return pif.findAll();
    }

    public List<Registation> getAllRegistation() {
        return rf.findAll();
    }

    //add new ideas choice by coordinator
    public Shortlist dodeleteprojectchoice(Shortlist isl) {
        //do operation
        Project pis = isl.getIdeapicked();
        Registation supervisor = isl.getSupervise();
        Registation moderator = isl.getModerate();
        Registation student = isl.getStudy();
        if (pis != null & supervisor != null & moderator != null & student != null) {
            pis = pif.edit(pis);
            supervisor = rf.edit(supervisor);
            moderator = rf.edit(moderator);
            student = rf.edit(student);
            isl.setIdeapicked(pis);
            isl.setSupervise(supervisor);
            isl.setModerate(moderator);
            isl.setStudy(student);
            pis.getIdeaspool().add(isl);
            supervisor.getSupervisors().add(isl);
            moderator.getModerators().add(isl);
            student.getStudents().add(isl);
        }
        slf.create(isl);
        //return an appropriate object
        return isl;
    }
}
