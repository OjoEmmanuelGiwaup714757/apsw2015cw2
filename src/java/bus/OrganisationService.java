/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import ents.Organisation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pers.OrganisationFacade;

/**
 *
 * @author Pavilion
 */
@Stateless
public class OrganisationService {
    @EJB
    private  OrganisationFacade of;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Organisation addOrganisation(Organisation o) {
      if(true) {
          of.create(o);
      }  
      return o;
    }
    public List<Organisation> findAllOrganisation() {
        return of.findAll();
      }
}
