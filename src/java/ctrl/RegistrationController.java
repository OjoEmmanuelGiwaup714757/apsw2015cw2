/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import bus.RegistrationService;
import ents.Organisation;
import ents.Registation;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
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
 * @author Pavilion
 */
@Named(value = "registrationController")
@RequestScoped
public class RegistrationController {

    /**
     * Creates a new instance of RegistrationController
     */
    public RegistrationController() {
    }
    @EJB
    private RegistrationService rs;
    private Registation r = new Registation();

    private String passwordConfirm;
    private String loginerroemsg;
    private String firstname;
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    Map<String, Object> sessionMap = externalContext.getSessionMap();
    private Organisation o = new Organisation();
    Calendar calendar = Calendar.getInstance();
    Date accountCreatedDateObject = new Date(calendar.getTime().getTime());
    private long a = 1;

    public Registation getR() {
        return r;
    }

    public void setR(Registation r) {
        this.r = r;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getLoginerroemsg() {
        return loginerroemsg;
    }

    public void setLoginerroemsg(String loginerroemsg) {
        this.loginerroemsg = loginerroemsg;
    }

    public Organisation getO() {
        return o;
    }

    public void setO(Organisation o) {
        this.o = o;
    }

    public void clearAllFields() {
        r.setFirstName("");
        r.setPassword("");
        r.setStudentId("");
        r.setSurname("");
        r.setUsername("");
    }

    public String dologin() {
        if ((rs.memberLoginStatusB(r).equals("Sucess"))) {
//            List<Registration> userList = rs.fetchMemberByUsernamePassword(r);
            List<Registation> userList = rs.fetchMemberByUsername(r);
            for (Registation m : userList) {
                sessionMap.put("userregcode", m.getId());
                sessionMap.put("userfname", m.getFirstName());
                sessionMap.put("userlname", m.getSurname());
                sessionMap.put("useruname", m.getUsername());
                sessionMap.put("useremail", m.getEmailaddr());
                sessionMap.put("loginusername", m.getFirstName() + " " + m.getSurname());
                sessionMap.put("userstatus", m.getUserstatus());
                sessionMap.put("Personstatusid", m.getPersonstatusid());
                sessionMap.put("iscoordinator", m.getIscoordinator());
                sessionMap.put("student", m);
            }
            return "/view/switchboard?faces-redirect=true";
        } else {
            loginerroemsg = "Either Username or Password is wrong. Try again!";
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Either Username or Password is wrong. Try again!"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(" "));
            return "index";
        }
    }

    public String doAddUser() {
        String myemail=r.getEmailaddr();
        r.setBelongOrg(o);
        r.setAccountcreateddate(accountCreatedDateObject);
        r.setPersonstatusid(a);
        r.setRegistrationstatus(a);
        rs.addUser(r);
        String emailheader = "SUMS Registration Confirmation for " + r.getFullName();
        fireEmail(myemail, emailheader, "This is an email from our SUMS Registration module. )");

        clearAllFields();
        return "/index?faces-redirect=true";
    }

    public String getUNameSession() {
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        return "Hi, " + (String) sessionMap.get("loginusername");
    }

    public String doLogout() {
        return "/index?faces-redirect=true";
    }

    public String loadRegistration() {
        return "/view/registration?faces-redirect=true";
    }

    public String loadOrganisation() {
        return "/view/organisation?faces-redirect=true";
    }
    
    public String loadSwitchboard(){
        return "/view/switchboard?faces-redirect=true";
    }
    
    public String loadSelectIdea(){
        return "/view/coordinatorselectidea?faces-redirect=true";
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
