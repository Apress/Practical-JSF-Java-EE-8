/*
 * **************************************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved.
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * For any other usage, please contact the author.
 * michael.mueller@mueller-bruehl.de
 * **************************************************************************
 */
package de.muellerbruehl.alumni.gui.classroom;

import de.muellerbruehl.alumni.gui.user.UserController;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@ViewScoped
@Named
public class ClassroomController implements Serializable{
    
    @Inject private UserController _userController;
    private String _classroom;
    
    @PostConstruct
    private void init(){
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        _classroom = "" + params.get("id");
        
    }

    public String getClassroom() {
        return _classroom;
    }
    
    public String getUserDisplayName (){
        //return _userController.getAccount().getDisplayName();
        return "###### Test";
    }
}
