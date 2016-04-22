/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import ents.Milestones;
import ents.Milestonetemplate;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pers.MilestoneFacade;
import pers.MilestonetemplateFacade;

/**
 *
 * @author Gooloo
 */
@Stateless
public class MilestoneTemplateService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
        private MilestonetemplateFacade mtf;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Milestonetemplate addMilestonetemplate(Milestonetemplate mt) {
      if(true) {
          mtf.create(mt);
      }  
      return mt;
    }
    
    public List<Milestonetemplate> findAllMilestonetemplate() {
        return  mtf.findAll();
    }
//    public List<Milestonetemplate> findAllActiveMilestonetemplate() {
//        return  mf.findActive();
//    }
    //method to delete a person
    public void deleteMilestonetemplate(Milestonetemplate m) {
        mtf.remove(m);
    }
    
    public  Milestonetemplate updateMilestonetemplate(Milestonetemplate mt) {
        mtf.edit(mt);
        return mt;
    }
    
    public List<Milestonetemplate> findAllMilestone() {
        return  mtf.findAll();
    }
    
    
}
