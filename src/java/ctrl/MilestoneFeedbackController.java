/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Gooloo
 */
@Named(value = "milestoneFeedbackController")
@RequestScoped
public class MilestoneFeedbackController {

    /**
     * Creates a new instance of MilestoneFeedbackController
     */
    public MilestoneFeedbackController() {
    }
    
    public String loadmilestone() {
        return "/view/milestonefeedback?faces-redirect=true";
    }
    
    
}
