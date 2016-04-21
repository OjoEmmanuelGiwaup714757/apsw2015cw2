/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import bus.OrganisationService;
import static ctrl.AbstractController.getSelectItems;
import ents.Organisation;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import pers.OrganisationFacade;

/**
 *
 * @author Pavilion
 */
@Named(value = "organisationController")
@RequestScoped
public class OrganisationController {

    /**
     * Creates a new instance of OrganisationController
     */
    public OrganisationController() {
    }
    @EJB
        private OrganisationService os;
        private Organisation o = new Organisation();
        private String addOrganisationSuccess;
        private OrganisationFacade of;

    public String getAddOrganisationSuccess() {
        return addOrganisationSuccess;
    }

    public void setAddOrganisationSuccess(String addOrganisationSuccess) {
        this.addOrganisationSuccess = addOrganisationSuccess;
    }
    
    public SelectItem[] getItemsAvailableSelectOneOrg() {
        return getSelectItems(os.findAllOrganisation(), true);
    }

    public OrganisationFacade getOf() {
        return of;
    }

    public void setOf(OrganisationFacade of) {
        this.of = of;
    }

    public Organisation getO() {
        return o;
    }

    public void setO(Organisation o) {
        this.o = o;
    }
    public void clearAllFields() {
        o.setOrganisationname("");
        o.setOrganisationaccountdesc("");
        o.setOrganisationaddress("");
        o.setOrganisationpostcode("");
    }
    public String doAddOrganisation() {
        os.addOrganisation(o);
        addOrganisationSuccess = "The organisation is successfully added";
        clearAllFields();
//        return "/view/registration?face-redirect=true";
        return "";
    }

    public List<Organisation> getAllOrganisation() {
            return os.findAllOrganisation();
    }
    
     public String loadRegistration() {
        return "/view/registration?face-redirect=true";
    }
}
