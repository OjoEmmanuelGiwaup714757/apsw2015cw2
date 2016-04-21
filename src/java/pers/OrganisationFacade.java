/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers;

import ents.Organisation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pavilion
 */
@Stateless
public class OrganisationFacade extends AbstractFacade<Organisation> {

    @PersistenceContext(unitName = "apsw2015cw2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

           
    public List<Organisation> findOrgAvailable() {
        Query q = em.createQuery("SELECT o FROM Organisation o");
        return q.getResultList();
    }
    
    
    public OrganisationFacade() {
        super(Organisation.class);
    }
    
}
