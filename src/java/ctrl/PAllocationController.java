/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import bus.PAllocationService;
import ents.Shortlist;
import ents.Project;
import ents.Registation;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Administrator
 */
@Named(value = "pAllocationController")
@RequestScoped
public class PAllocationController extends MessageController {

    /**
     * Creates a new instance of PAllocationController
     */
    public PAllocationController() {
    }

    @EJB
    private PAllocationService pas;
    private Shortlist sl = new Shortlist();
    private Project p = new Project();
    private Registation supervisor = new Registation();
    private Registation moderator = new Registation();
    private Registation student = new Registation();
    private long userstatus;
    private long iscoordinator;
    private long userid;
    Calendar calendar = Calendar.getInstance();
    Date projectselectionDateObject = new Date(calendar.getTime().getTime());
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    Map<String, Object> sessionMap = externalContext.getSessionMap();

    public List<Shortlist> getAllShortlist() {
        List<Shortlist> lls = pas.getAllShortlist();
        return lls;
    }

    public List<Shortlist> findShortlistBystudent() {
        userid = (Long) sessionMap.get("userregcode");
        return pas.findShortlistBystudent(userid);
    }

    public List<Project> getAllProject() {
        return pas.getAllProject();
    }

    public Shortlist getSl() {
        return sl;
    }

    public void setSl(Shortlist sl) {
        this.sl = sl;
    }

    public Project getP() {
        return p;
    }

    public void setP(Project p) {
        this.p = p;
    }

    public Registation getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Registation supervisor) {
        this.supervisor = supervisor;
    }

    public Registation getModerator() {
        return moderator;
    }

    public void setModerator(Registation moderator) {
        this.moderator = moderator;
    }

    public Registation getStudent() {
        return student;
    }

    public void setStudent(Registation student) {
        this.student = student;
    }

    //save project choice by student.
    public String dosaveprojectchoice() {
        if (p != null & supervisor != null & moderator != null) {
            sl.setIdeapicked(p);
            sl.setSupervise(supervisor);
            sl.setModerate(moderator);
            student = (Registation) sessionMap.get("student");
            sl.setStudy(student);
            sl.setSubmissiondate(projectselectionDateObject);
        }
        pas.addnewideaschoice(sl);
        addInfo(sl.getIdeapicked().getTitle() + " supervised by " + sl.getSupervise().getFullName() + " and moderated by " + sl.getModerate().getFullName() + " added successfully!");

        return "";
    }

    //save project choice by coordinator
    public String dosaveprojectchoiceBycoordinator() {
        if (p != null & supervisor != null & moderator != null & student != null) {
            sl.setIdeapicked(p);
            sl.setSupervise(supervisor);
            sl.setModerate(moderator);
            sl.setStudy(student);
            sl.setSubmissiondate(projectselectionDateObject);
        }
        pas.addnewideaschoicebycoordinator(sl);
        addInfo(sl.getIdeapicked().getTitle() + " supervised by " + sl.getSupervise().getFullName() + " and moderated by " + sl.getModerate().getFullName() + " for " + sl.getStudy().getFullName() + " added successfully!");

        return "";
    }

    //allocate project choice, set shortlist status =1 
    public void allocateProjectChoice(Shortlist sl) {
        if (sl.getStudy().getPersonstatusid() != 2 && sl.getIdeapicked().getStatus() != 3) {
            pas.allocateProjectChoice(sl);
            String mymail=(String) sessionMap.get("useremail");
            String emailheader = "SUMS Project Idea Allocation Confirmation for " + sl.getIdeapicked().getTitle()+" and "+sl.getStudy().getFullName();
            fireEmail(mymail, emailheader, "This is an email from our SUMS Project Idea  Allocation module. )");
            addInfo(sl.getIdeapicked().getTitle() + " has been allocated to " + sl.getStudy().getFullName() + " successfully!");
        } else if (sl.getStudy().getPersonstatusid() == 2) {
            addInfo(sl.getStudy().getFullName() + " had been allocated already!");
        } else if (sl.getIdeapicked().getStatus() == 3) {
            addInfo(sl.getIdeapicked().getTitle() + " had been allocated already!");
        }
    }

    public String displaySelectionModule() {
        userstatus = (Long) sessionMap.get("userstatus");
        iscoordinator = (Long) sessionMap.get("iscoordinator");
        if (userstatus == 1) {
            return "/view/selectidea?faces-redirect=true";
        } else if (iscoordinator == 2) {
            return "/view/coordinatorselectidea?faces-redirect=true";
        } else {
            return "";
        }
    }

    public String displayAllcationModule() {
        iscoordinator = (Long) sessionMap.get("iscoordinator");
        if (iscoordinator == 2) {
            return "/view/coordinatorAllocation?faces-redirect=true";
        } else {
            return "";
        }
    }

    //email code segment begins here
    //public static void fireEmail(final String toEmail, final String body) {
    public static void fireEmail(final String toEmail, final String msgcaption, final String body) {
        new Thread() {
            public void run() {
                try {
                    //please change the email and password by ur gmail account and password
                    final String emailusername = "kikigiwa@gmail.com";
                    final String emailpassword = "wealthy";
                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.imap.ssl.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");
                    Session session = Session.getInstance(props,
                            new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    emailusername, emailpassword);
                        }
                    });

                    MimeMessage msg = new MimeMessage(session);
                    // set message headers
                    msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                    msg.addHeader("format", "flowed");
                    msg.addHeader("Content-Transfer-Encoding", "8bit");
                    msg.setFrom(new InternetAddress("kikigiwa@gmail.com", "Group 1"));
                    msg.setReplyTo(InternetAddress.parse(
                            "kikigiwa@gmail.com", false));
                    //msg.setSubject("Please Confirm your SUMS Registration - Group X", "UTF-8");
                    msg.setSubject(msgcaption, "UTF-8");
                    msg.setText(body, "UTF-8");
                    msg.setSentDate(new Date());
                    msg.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(toEmail, false));
                    System.out.println("Message is ready");
                    Transport.send(msg);
                    System.out.println("End =======================");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
