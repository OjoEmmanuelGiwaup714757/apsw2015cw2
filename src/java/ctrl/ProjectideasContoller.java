/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import bus.ProjectIdeaService;
import ents.Project;
import java.text.SimpleDateFormat;
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
 * @author Pavilion
 */
@Named(value = "projectideasContoller")
@RequestScoped
public class ProjectideasContoller {

    /**
     * Creates a new instance of ProjectideasContoller
     */
    public ProjectideasContoller() {
    }
   @EJB
        private ProjectIdeaService pis;
//        private IdeacategoriesService ics;
        private Project pi = new Project();
        private String titlesearchcriteria = "";
        private String categorysearchcriteria = "1";
        private String searchgrp = "1";
        private Date periodstartdate = new Date();
        private Date periodenddate = new Date();
        private Date pdate1 = new Date();
        private Date pdate2 = new Date();
        private  String pititle;
//        private Ideascategories selectedIdeacategories;
//        private Ideascategories ic = new Ideascategories();
        private String userid = "714757";
        private String submitideasucess;
        private String ideasuccessimgurl;
        private String emailheader;
        private String loginuseremailtemp;
        private  long useridtemp;
        Calendar calendar = Calendar.getInstance();
        java.sql.Date ideaDateObject = new java.sql.Date(calendar.getTime().getTime());
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();

    public Project getPi() {
        return pi;
    }

    public void setPi(Project pi) {
        this.pi = pi;
    }

    public String getTitlesearchcriteria() {
        return titlesearchcriteria;
    }

    public void setTitlesearchcriteria(String titlesearchcriteria) {
        this.titlesearchcriteria = titlesearchcriteria;
    }

    public String getCategorysearchcriteria() {
        return categorysearchcriteria;
    }

    public void setCategorysearchcriteria(String categorysearchcriteria) {
        this.categorysearchcriteria = categorysearchcriteria;
    }

    public String getSearchgrp() {
        return searchgrp;
    }

    public void setSearchgrp(String searchgrp) {
        this.searchgrp = searchgrp;
    }

    public Date getPeriodstartdate() {
        return periodstartdate;
    }

    public void setPeriodstartdate(Date periodstartdate) {
        this.periodstartdate = periodstartdate;
    }

    public Date getPeriodenddate() {
        return periodenddate;
    }

    public void setPeriodenddate(Date periodenddate) {
        this.periodenddate = periodenddate;
    }

    public Date getPdate1() {
        return pdate1;
    }

    public void setPdate1(Date pdate1) {
        this.pdate1 = pdate1;
    }

    public Date getPdate2() {
        return pdate2;
    }

    public void setPdate2(Date pdate2) {
        this.pdate2 = pdate2;
    }

    public String getPititle() {
        return pititle;
    }

    public void setPititle(String pititle) {
        this.pititle = pititle;
    }

//    public Ideascategories getSelectedIdeacategories() {
//        return selectedIdeacategories;
//    }

//    public void setSelectedIdeacategories(Ideascategories selectedIdeacategories) {
//        this.selectedIdeacategories = selectedIdeacategories;
//    }

//    public Ideascategories getIc() {
//        return ic;
//    }

//    public void setIc(Ideascategories ic) {
//        this.ic = ic;
//    }
    public String getUserid() {
        return userid;
    }

    public String getSubmitideasucess() {
        return submitideasucess;
    }

    public void setSubmitideasucess(String submitideasucess) {
        this.submitideasucess = submitideasucess;
    }

    public String getIdeasuccessimgurl() {
        return ideasuccessimgurl;
    }

    public void setIdeasuccessimgurl(String ideasuccessimgurl) {
        this.ideasuccessimgurl = ideasuccessimgurl;
    }

    public String getEmailheader() {
        return emailheader;
    }

    public void setEmailheader(String emailheader) {
        this.emailheader = emailheader;
    }

    public String getLoginuseremailtemp() {
        return loginuseremailtemp;
    }

    public void setLoginuseremailtemp(String loginuseremailtemp) {
        this.loginuseremailtemp = loginuseremailtemp;
    }

    public long getUseridtemp() {
        return useridtemp;
    }

    public void setUseridtemp(long useridtemp) {
        this.useridtemp = useridtemp;
    }
    
    //clear all fields
    public void clearAllFields() {
       pi.setTitle("");
      
       pi.setAim("");
       pi.setPidesc("");
       pi.setAcademicquestion("");
       pi.setPicategory(1L);
       pi.setNoofstudent(1L);
//       pi.setSubmissiondate(Calendar.getInstance().getTime());
    }
    //add a project idea.
    public String doaddProjectIdea(){
        //fetch login userid from session
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        useridtemp = (long) sessionMap.get("userregcode");
            pi.setOwnerid(useridtemp);
//            pi.setSubmissiondate(ideaDateObject);
            pi.setCreationdate(ideaDateObject);
            pis.addIdeas(pi);
            submitideasucess = "Your Project Idea was submitted successfully.";
            ideasuccessimgurl = "/resourses/images/success.jpg";
            emailheader = "SUMS Project Idea Submission Confirmation for " + pi.getTitle();
            loginuseremailtemp = (String) sessionMap.get("useremail");
            clearAllFields();
//            fireEmail("Bin.Jiang@myport.ac.uk", emailheader, "Testigng! Testing!!\nThis is an email from our SUMS Project Idea module. :)");
            fireEmail(loginuseremailtemp, emailheader, "Testigng! Testing!!\nThis is an email from our SUMS Project Idea module. :)");
            return "";
    }
    // returns the details of a member whose registration ID is selected
    public Project getIdeaDetails() {
      return pi;
    }
    public String showDetails(Project projectidea) {
        this.pi = projectidea;
        return "ideadetails";
    }
    public String showDetailsUnprocess(Project projectidea) {
        this.pi = projectidea;
        return "ideaapproval";
    }
    public String updateProjectIdea(){
        this.pi = pis.updatePIdea(pi);
        return "pisearch";
    }
    public List<Project> getAllIdeas() {
            return pis.findAllIdeas();
    }
    //get all ideas by a user
    public List<Project> getAllIdeasByOneOwner() {
            useridtemp = (long) sessionMap.get("userregcode");
            return pis.findAllProjectIdeaByAUser(useridtemp);
    }              
//        public List<Ideascategories> getAllIdeacategories() {
//            return ics.findAllIcategories();
//        }
        public String loadNewidearpt() {
            return "NewIdeaRpt";
        }
        
        public String loadIdeaSearchScrn() {
            return "pisearch";
//            return "/view/pisearch?faces-redirect=true";
        }
        //search all approved ideas
        public String searchIdea() {
                return "pisearch";
//                return "/view/pisearch?faces-redirect=true";
        }
        //search all unprocessed ideas
        public String searchUnprocessedIdeas() {
                return "pisearchproc";
//                return "/view/pisearch?faces-redirect=true";
        }
         public String displayProjectIdeaModule() {
                return "/view/pisearch?faces-redirect=true";
        }
          
         public String displayProjectSubmitidea() {
                return "/view/submitidea?faces-redirect=true";
//                  return "submitidea";
        }
         public String displayProjectIdeaApproval() {
        return "/view/ideaapproval?faces-redirect=true";
    }
          public String displayProjectIdeaProcessing() {
                return "/view/ideaprocessing?faces-redirect=true";
        }
        public List<Project> getIdeasUntreated() {
            return pis.findAllIdeas();
        }
        public List<Project> getSearchAllIdeas() {
//            return pis.findAllIdeas();
            switch (searchgrp) {
                case "1": //project iead search by Idea Title
                    if(titlesearchcriteria.isEmpty() || titlesearchcriteria == null ) {
                    return pis.findAllIdeas();
                    } else {
                    return  pis.findAllProjectIdeaByAnyFieldCriteria(this.titlesearchcriteria);
                    }
                case "2": //project iead search by category
                    if(categorysearchcriteria.isEmpty() || categorysearchcriteria == null) {
                    return pis.findAllIdeas();
                    } else {
                        userid = userid + categorysearchcriteria;
                    return  pis.findAllProjectIdeaByGroup(Integer.parseInt(categorysearchcriteria));
                    }
                default: //project iead search by project Idea submission date
                    if(periodstartdate == null || periodenddate == null ) {
                    return pis.findAllIdeas();
                    } else {
//                        userid = userid + periodstartdate.getYear();                        
                        SimpleDateFormat mydate = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat mydate1 = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat mydatego = new SimpleDateFormat("yyyy-MM-dd");
                        String mystring =mydate.format(periodenddate);
                        String mystring2 =mydate1.format(periodstartdate);
                        String mystring3 =mydate1.format(periodenddate);
                        userid = mystring2;                       
                        java.util.Date datefrom = null, dateto = null;                        
                        try {
                            pdate1 = (Date) mydate1.parse(mystring2);
                            pdate2 = (Date) mydate1.parse(mystring3);
                            datefrom = (java.util.Date)mydatego.parse(mystring2);
                            dateto = (java.util.Date)mydatego.parse(mystring3);
//                            Calendar cal = Calendar.getInstance();
//                            cal.setTime(datefrom);
                            userid = userid + dateto;                            
                        } catch (Exception e) {
                        }
//                    return  pis.findAllProjectIdeaWithinPeriod(periodstartdate, periodenddate);
                    return  pis.findAllProjectIdeaWithinPeriod(datefrom, dateto);
//                    return  pis.findAllProjectIdeaWithinPeriod(pdate1, pdate2);
                    }
            }
        }
        public List<Project> getSearchAllIdeasUntreated() {
//            return pis.findAllIdeas();
            switch (searchgrp) {
                case "1": //project iead search by Idea Title
                    if(titlesearchcriteria.isEmpty() || titlesearchcriteria == null ) {
                    return pis.findAllUntrestedProjectIdeas();
                    } else {
                    return  pis.findAllUnprocessProjectIdeaByAnyFieldCriteria(this.titlesearchcriteria);
                    }
                case "2": //project iead search by category
                    if(categorysearchcriteria.isEmpty() || categorysearchcriteria == null) {
                    return pis.findAllUntrestedProjectIdeas();
                    } else {
                        userid = userid + categorysearchcriteria;
                    return  pis.findAllUnprocessProjectIdeaByGroup(Integer.parseInt(categorysearchcriteria));
                    }
                default: //project iead search by project Idea submission date
                    if(periodstartdate == null || periodenddate == null ) {
                    return pis.findAllUntrestedProjectIdeas();
                    } else {
//                        userid = userid + periodstartdate.getYear();                        
                        SimpleDateFormat mydate = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat mydate1 = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat mydatego = new SimpleDateFormat("yyyy-MM-dd");
                        String mystring =mydate.format(periodenddate);
                        String mystring2 =mydate1.format(periodstartdate);
                        String mystring3 =mydate1.format(periodenddate);
                        userid = mystring2;                       
                        java.util.Date datefrom = null, dateto = null;                        
                        try {
                            pdate1 = (Date) mydate1.parse(mystring2);
                            pdate2 = (Date) mydate1.parse(mystring3);
                            datefrom = (java.util.Date)mydatego.parse(mystring2);
                            dateto = (java.util.Date)mydatego.parse(mystring3);
//                            Calendar cal = Calendar.getInstance();
//                            cal.setTime(datefrom);
                            userid = userid + dateto;                            
                        } catch (Exception e) {
                        }
//                    return  pis.findAllProjectIdeaWithinPeriod(periodstartdate, periodenddate);
                    return  pis.findAllProjectIdeaWithinPeriod(datefrom, dateto);
//                    return  pis.findAllProjectIdeaWithinPeriod(pdate1, pdate2);
                    }
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
					/*
					 * props.put("mail.smtp.host", "smtp.gmail.com");
					 * props.put("mail.smtp.socketFactory.port", "465");
					 * props.put("mail.smtp.socketFactory.class",
					 * "javax.net.ssl.SSLSocketFactory");
					 * props.put("mail.smtp.auth", "true");
					 * props.put("mail.smtp.port", "465");
					 */

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

					msg.setFrom(new InternetAddress("kikigiwa@gmail.com",
							"Group 1"));

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

        //email segment ends here
        public String doEmail(){
            emailheader = "SUMS Project Idea Submission Confirmation for " + pi.getTitle();
            fireEmail("kikigiwa@gmail.com", emailheader, "Testigng! Testing!!\nThis is an email from our SUMS Project Idea module. :)");
            return "";
        }
                public String backSwitchboardPage(){
        return "/view/switchboard?faces-redirect=true";
        }
}
