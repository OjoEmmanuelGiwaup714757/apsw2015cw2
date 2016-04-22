/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import bus.RegistrationService;
import ents.Registation;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import pers.RegistationFacade;

/**
 *
 * @author up792072
 */
@Named(value = "registationBean")
@RequestScoped
public class RegistationBean extends AbstractController<Registation, RegistationFacade> {

    @EJB
    private RegistationFacade rf;
    private RegistrationService rs;
    private Registation r = new Registation();

    public RegistationFacade getRf() {
        return rf;
    }

    /**
     * Creates a new instance of SuperandmoderBean
     */
    public RegistationBean() {
    }

    public Registation getR() {
        return r;
    }

    public void setR(Registation r) {
        this.r = r;
    }

    public SelectItem[] getItemsAvailableSelectOneBySupervisor() {
        return getSelectItems(rf.findSupervisors(), true);
    }

    public SelectItem[] getItemsAvailableSelectOneByModerator() {
        return getSelectItems(rf.findModerators(), true);
    }

    public SelectItem[] getItemsAvailableSelectOneByStudent() {
        return getSelectItems(rf.findStudents(), true);
    }

    @Override
    public RegistationFacade getFacade() {
        return rf;
    }

}
