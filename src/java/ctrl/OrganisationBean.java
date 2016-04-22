/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import static ctrl.AbstractController.getSelectItems;
import ents.Organisation;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import pers.OrganisationFacade;

/**
 *
 * @author Administrator
 */
@Named(value = "organisationBean")
@RequestScoped
public class OrganisationBean extends AbstractController<Organisation, OrganisationFacade>{

    /**
     * Creates a new instance of OrganisationBean
     */
    public OrganisationBean() {
    }
    
    @EJB
    private OrganisationFacade of;

    public OrganisationFacade getOf() {
        return of;
    }

    public void setOf(OrganisationFacade of) {
        this.of = of;
    }
    
        public SelectItem[] getItemsAvailableSelectOneOrg() {
        return getSelectItems(of.findAll(), true);
    }

    @Override
    public OrganisationFacade getFacade() {
        return of;
    }
}
