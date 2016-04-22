/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import ents.Milestones;
import ents.Milestonetemplate;
import ents.Project;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pers.MilestoneFacade;

/**
 *
 * @author Gooloo
 */
@Stateless
public class MilestoneService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
//        private MilestonetemplateFacade mf;
    private MilestoneFacade mf;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Milestones submitMilestone(Milestones m) {
      if(true) {
          mf.create(m);
      }  
      return m;
    }
    
    //find milestone by a milestonetamplate id
    public List<Milestones> findAllMilestoneBymtid(long stuid){
         return mf.findMilestonesBymtid(stuid);
    }
    
     public List<Milestones> getAllMilestones() {
            return mf.findAll();
    }

    
}
