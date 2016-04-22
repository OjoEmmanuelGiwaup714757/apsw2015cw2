/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers;

import ents.Milestones;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Gooloo
 */
@Stateless
public class MilestoneFacade extends AbstractFacade<Milestones> {

    @PersistenceContext(unitName = "apsw2015cw2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MilestoneFacade() {
        super(Milestones.class);
    }
    
     // find project ideas by project onerid   
    public List<Milestones> findMilestonesBymtid(long stuid) {
        Query q = em.createQuery("SELECT m FROM Milestones");
      
        //q.setParameter("milID", 1);
        q.executeUpdate();
        
        return q.getResultList();
    }
    
    
}
