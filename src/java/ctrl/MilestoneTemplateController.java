/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import bus.MilestoneTemplateService;
import ents.Milestones;
import ents.Milestonetemplate;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gooloo
 */
@Named(value = "milestoneTemplateController")
@RequestScoped
public class MilestoneTemplateController {

    /**
     * Creates a new instance of ProjectmonitoringController
     */
    public MilestoneTemplateController() {
    }
    
    @EJB
    private MilestoneTemplateService mts;
    private Milestonetemplate mt = new Milestonetemplate();
    private Milestones mi = new Milestones();
    private String milestoneTemplateStatus;
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private Date currentdate = new Date();
    private String studentidTemp = "up716777";
    
     
    public Date getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(Date currentdate) {
        this.currentdate = currentdate;
    }
    
    private String addmilestonesucess;

    public String getAddmilestonesucess() {
        return addmilestonesucess;
    }

    public void setAddmilestonesucess(String addmilestonesucess) {
        this.addmilestonesucess = addmilestonesucess;
    }

    public Milestonetemplate getMt() {
        return mt;
    }

    public void setMt(Milestonetemplate mt) {
        this.mt = mt;
    }

    public Milestones getMi() {
        return mi;
    }

    public void setMi(Milestones mi) {
        this.mi = mi;
    }

    public String getStudentidTemp() {
        return studentidTemp;
    }

    public void setStudentidTemp(String studentidTemp) {
        this.studentidTemp = studentidTemp;
    }
    
    public void resetallInputfields(){
        mt.setMilestonetemplatedesc("");
        mt.setMilestonetemplateactivedate("");
        mt.setMilestonetemplateduedate("");
        mt.setMilestonetemplatetitle("");
    }

    //add a project milestone.
    public String doaddMilestonetemplate(){
        mts.addMilestonetemplate(mt);
        resetallInputfields();
        milestoneTemplateStatus = "Milestone added successfully!";
          //  successimgurl = "/resources/images/success.jpg";
//                return "/view/addMilestoneSuccess?faces-redirect=true";
        return ""; 
    }
 
    public List<Milestonetemplate> getAllMilestonetemplate() {
            return mts.findAllMilestonetemplate();
    }
    
    public String editMilestonetemplate() {
        this.mt = mts.updateMilestonetemplate(mt);
        resetallInputfields();
        milestoneTemplateStatus = "Milestone Edited successfully!";
        return "milestonetemplate";
    }
    
    public List<Milestonetemplate> getAllActiveMilestonetemplate() {
            return mts.findAllMilestone();
    }
 
    public String removeMilestonetemplate(Milestonetemplate mt) {
        mts.deleteMilestonetemplate(mt);
        return "";
    }
    
    public String showDetails(Milestonetemplate mt){
        this.mt = mt;
        return "milestonedetails";
    }
    
    public String showeditMilestonetemplate(Milestonetemplate mt){
        this.mt = mt;
        return "editmilestonetemplate";
    }
    
    public String displayProjectMonitoringModule(){
        return "/view/monitoring?faces-redirect=true";
    }

    public String getMilestoneTemplateStatus() {
        return milestoneTemplateStatus;
    }

    public void setMilestoneTemplateStatus(String milestoneTemplateStatus) {
        this.milestoneTemplateStatus = milestoneTemplateStatus;
    }
    
    
    
   

}
