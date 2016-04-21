/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers;

import ents.Milestonetemplate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gooloo
 */
@Stateless
public class MilestonetemplateFacade extends AbstractFacade<Milestonetemplate> {

    @PersistenceContext(unitName = "apsw2015cw2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MilestonetemplateFacade() {
        super(Milestonetemplate.class);
    }
    
}
