/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import bus.MilestoneService;
import ents.Milestones;
import ents.Milestonetemplate;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;

/**
 *
 * @author Gooloo
 */
@Named(value = "milestoneController")
@RequestScoped
public class MilestoneController {
    @EJB
    private MilestoneService ms;
    private Milestones mi = new Milestones();
    private Milestonetemplate mt = new Milestonetemplate();
    private String milestoneStatus;
    private Part file;
    private Timestamp timestamp;
    private Date date;
    private Date output;
    /**
     * Creates a new instance of MilestoneController
     */
    public MilestoneController() {
    }
    
    public String dosubmitMilestone() throws IOException{ 
            mi.setStudentid("up738999");
            mt.getMilestonetemplateid();
            mi.setMilestonetemplateid(3333);
            mi.setSubmissiondate(currentDate);
            uploadfile();
            ms.submitMilestone(mi);
            milestoneStatus = "Milestone Submited successfully!";
            return "milestones";
    }
    
    public String getMilestoneStatus() {
        return milestoneStatus;
    }

    public void setMilestoneStatus(String milestoneStatus) {
        this.milestoneStatus = milestoneStatus;
    }
    
    
    public Date getDate() {
     return new Date(timestamp.getTime());
    }

    public Milestones getMi() {
        return mi;
    }

    public void setMi(Milestones mi) {
        this.mi = mi;
    }

    public Milestonetemplate getMt() {
        return mt;
    }

    public void setMt(Milestonetemplate mt) {
        this.mt = mt;
    }
    
    public String submitmilestonepage(Milestonetemplate mt){
        this.mt = mt;
        return "submitmilestone";
    }
    
    private Date currentDate = new Date();

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    
    public String uploadfile() throws IOException {
        file.write(getFileName(file));
        File afile = new File("/Users/Gooloo/GlassFish_Server/glassfish/domains/domain1/generated/jsp/apsw2015cw2/" + getFileName(file));
        afile.renameTo(new File("/Users/Gooloo/Desktop/Upload/" + getFileName(file)));
        //mi.setMilestoneuploadfilepath("/Users/Gooloo/Desktop/Upload/" + getFileName(file));
        //mi.setMilestoneuploadfilepath("3333333");
        //Map<String, Object> sessionMap = externalContext.getSessionMap();
        //mi.setMilestonetimestamp("ssss");
        //mi.setMilestoneremark("ddd");
        //mi.setMilestonetemplateid(33);
        mi.setFilepart(afile.toString());
        milestoneStatus = "Milestone added successfully!";
        return "milestoneStatus";
    }
    
    private  static String getFileName(Part part){
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if(cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);                
            }
        }
        return null;
    }
    
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}
