/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import bus.RegistrationService;
import ents.Registation;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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
    private String registrationSuccess;
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    Map<String, Object> sessionMap = externalContext.getSessionMap();
    private Long idname;

    public Registation getR() {
        return r;
    }

    public void setR(Registation r) {
        this.r = r;
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

    public String getRegistrationSuccess() {
        return registrationSuccess;
    }

    public void setRegistrationSuccess(String registrationSuccess) {
        this.registrationSuccess = registrationSuccess;
    }

    public Long getIdname() {
        return idname;
    }

    public void setIdname(Long idname) {
        this.idname = idname;
    }

    public void clearAllFields() {
        r.setFirstName("");
        r.setPassword("");
        r.setStudentId("");
        r.setSurname("");
        r.setUsername("");
    }

    public String doAddUser() {
        if (r.getPassword().equals(this.passwordConfirm)) {
            rs.createNewUser(r);
            registrationSuccess = "You are successfully registered.";
            clearAllFields();
            return "";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password and Password Confirm field must be the same!"));
            return "";
        }
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

    public String getUNameSession() {
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        return "Hi, " + (String) sessionMap.get("loginusername");
    }

    public String updatePassword() {
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        idname = (Long) sessionMap.get("userregcode");
//        r.(idname);
        return "";

    }

    public String doLogout() {
        return "/index?faces-redirect=true";
    }
    
    public String loadOrganisation() {
        return "/view/organisation?face-redirect=true";
    }

    public String displayUpdatePassword() {
        return "/view/updatePassword?faces-redirect=true";
    }

    public String displayUpdatePersonalInfo() {
        return "/view/updatePersonalInfo?faces-redirect=true";
    }
    
    public String loadSwitchBoard(){
        return "/view/switchboard?faces-redirect=true";
    }
}
