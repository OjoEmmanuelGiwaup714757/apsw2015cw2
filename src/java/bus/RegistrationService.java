/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import ents.Organisation;
import ents.Registation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pers.OrganisationFacade;
import pers.RegistationFacade;

/**
 *
 * @author Pavilion
 */
@Stateless
public class RegistrationService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private RegistationFacade rf;
    @EJB
    private OrganisationFacade of;

    //member can login status
    public String memberLoginStatus(Registation r) {
        List<Registation> list = rf.findMemberByUsernamePassword(r.getUsername(), r.getPassword());
        if (list.isEmpty()) {
            return "Failure";
        } else {
            return "Sucess";
        }
    }

    public String memberLoginStatusB(Registation r) {
        List<Registation> list = rf.findMemberByUsername(r.getUsername());
        if (list.isEmpty()) {
            return "Failure";
        } else {
            return "Sucess";
        }
    }

    public Registation addUser(Registation r) {
        Organisation os = r.getBelongOrg();
        os = of.edit(os);
        r.setBelongOrg(os);
        os.getUser().add(r);
        rf.create(r);
        return r;
    }

    public List<Registation> fetchMemberByUsernamePassword(Registation r) {
        return rf.findMemberByUsernamePassword(r.getUsername(), r.getPassword());
    }

    public List<Registation> fetchMemberByUsername(Registation r) {
        return rf.findMemberByUsername(r.getUsername());
    }
}
