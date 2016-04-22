/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import ctrl.OrganisationBean;
import ctrl.OrganisationController;
import ents.Organisation;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pers.OrganisationFacade;


/**
 *
 * @author Pavilion
 */

    @FacesConverter(forClass = Organisation.class)
public class OrganisationConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        OrganisationBean organisationCtrl = (OrganisationBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "organisationBean");
        OrganisationFacade of = organisationCtrl.getOf();
        Long id = Long.decode(value);
        Organisation o = of.find(id);
        return o;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Organisation) {
            return ((Organisation) value).getId().toString();
        } else {
            throw new Error("object is not of type Organisation");
        }
    }

}

