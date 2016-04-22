/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import bus.MilestoneService;
import ents.Milestones;
import ents.Milestonetemplate;
import ents.Registation;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
    private Registation R = new Registation();
    private Milestones mi = new Milestones();
    private Milestonetemplate mt = new Milestonetemplate();
    private String milestoneStatus;
    private Part file;
    private Timestamp timestamp;
    private Date date;
    private Date output;
    private String myFilePath;
    private long useridtemp;
    private long studentidtemp;
    private long stuid;
    private long mtid;
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    /**
     * Creates a new instance of MilestoneController
     */
    public MilestoneController() {
    }
    
    public String dosubmitMilestone() throws IOException{ 
            mi.getComment();
            mi.setSubmissiondate(currentDate);
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            R = (Registation) sessionMap.get("student");

            uploadfile();

            mi.setFilepath(myFilePath);
            mi.setMilestoneowner(R);
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
    
    public String viewsubmitmilestonepage(Milestonetemplate mt){
       
        this.mt = mt;
        //mi =  ms.findAllMilestoneBymtid(mt.getId());
        return "viewsubmitmilestone";
    }
    
    public String submitmilestonepage(){
        //mi =  ms.findAllMilestoneBymtid(mt.getId());
        return "viewsubmitmilestone";
    }
    
    public List<Milestones> getMilestoneByID() {
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            stuid = (Long) sessionMap.get("userregcode");
            mtid = mt.getId();
            return ms.getAllMilestones();
    }
    
    public List<Milestones> getAllMilestones() {
            return ms.getAllMilestones();
    }
    
    private Date currentDate = new Date();

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public long getStudentidtemp() {
        return studentidtemp;
    }

    public void setStudentidtemp(long studentidtemp) {
        this.studentidtemp = studentidtemp;
    }
    
    public String uploadfile() throws IOException {
        file.write(getFileName(file));
        File afile = new File("/Users/Gooloo/GlassFish_Server/glassfish/domains/domain1/generated/jsp/apsw2015cw2/" + getFileName(file));
        afile.renameTo(new File("/Users/Gooloo/Desktop/Upload/" + getFileName(file)));
        
        milestoneStatus = "Milestone added successfully!";
        this.myFilePath = "/Users/Gooloo/Desktop/Upload/" + getFileName(file);
       
        return "milestoneStatus";
    }
    
    public String viewsubmitedmilestone(Milestonetemplate mt){
        this.mt = mt;
        return "viewsubmitmilestone";
    
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

    public String getMyFilePath() {
        return myFilePath;
    }

    public void setMyFilePath(String myFilePath) {
        this.myFilePath = myFilePath;
    }

    public long getUseridtemp() {
        return useridtemp;
    }

    public void setUseridtemp(long useridtemp) {
        this.useridtemp = useridtemp;
    }
    
    
    
    
 
    
    
}
