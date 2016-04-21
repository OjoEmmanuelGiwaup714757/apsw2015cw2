/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers;

import ents.Registation;
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
public class RegistationFacade extends AbstractFacade<Registation> {

    @PersistenceContext(unitName = "apsw2015cw2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistationFacade() {
        super(Registation.class);
    }
    public List<Registation> findMemberByUsernamePassword(String username, String password) {
        Query q = em.createQuery("SELECT r FROM Registation r WHERE r.username = :username AND r.password = :password");
        q.setParameter("username", username).setParameter("password", password);
        return q.getResultList();
    }
    
    public List<Registation> findMemberByUsername(String username) {
        Query q = em.createQuery("SELECT r FROM Registation r WHERE r.username = :username");
        q.setParameter("username", username);
        return q.getResultList();
    }
   
    //get supervisor
    public List<Registation> findSupervisors() {
        Query q = em.createQuery("SELECT r FROM Registation r WHERE r.isSupervisor=2");
        return q.getResultList();
    }

    //get supervisor
    public List<Registation> findModerators() {
        Query q = em.createQuery("SELECT r FROM Registation r WHERE r.isModerator=2");
        return q.getResultList();
    }
    
    //get students
    public List<Registation> findStudents() {
        Query q = em.createQuery("SELECT r FROM Registation r WHERE r.isModerator=1 AND r.isSupervisor=1");
        return q.getResultList();
    }
    
        public void allocateStudent(Registation student) {
        Query q = em.createQuery("UPDATE Registation r SET r.personstatusid=2 WHERE r.id=:studentid");
        q.setParameter("studentid", student.getId());
        q.executeUpdate();
    }
}
