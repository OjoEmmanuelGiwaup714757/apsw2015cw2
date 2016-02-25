/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers;

import ents.Milestonefeedback;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pavilion
 */
@Stateless
public class MilestonefeedbackFacade extends AbstractFacade<Milestonefeedback> {
    @PersistenceContext(unitName = "apsw2015cw2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MilestonefeedbackFacade() {
        super(Milestonefeedback.class);
    }
    
}
