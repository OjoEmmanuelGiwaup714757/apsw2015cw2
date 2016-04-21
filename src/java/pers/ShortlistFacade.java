/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers;

import ents.Shortlist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author up792072
 */
@Stateless
public class ShortlistFacade extends AbstractFacade<Shortlist> {

    @PersistenceContext(unitName = "apsw2015cw2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShortlistFacade() {
        super(Shortlist.class);
    }

    public void allocateChoice(Shortlist shortlist) {
        Query q = em.createQuery("UPDATE Shortlist sl SET sl.status='Allocated' WHERE sl.id=:shortlistid");
        q.setParameter("shortlistid", shortlist.getId());
        q.executeUpdate();
    }

    public List<Shortlist> findShortlistBystudent(long userid) {
        Query q = em.createQuery("SELECT sl FROM Shortlist sl WHERE sl.study.id=:userid");
        q.setParameter("userid", userid);
        return q.getResultList();
    }
//    public List<Shortlist> findShortlistAvailable() {
//        Query q = em.createQuery("SELECT s FROM Shortlist s WHERE s.status=0");
//        return q.getResultList();
//    }

//    public String updateShortlist(Shortlist sl) {
//        Query q = em.createQuery("UPDATE Shortlist s SET s.status=1 WHRER s =:sl");
//        q.executeUpdate();
//        return null;
//    }
}
