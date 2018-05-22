/***********************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 ***********************************************************/
package de.muellerbruehl.intermezzo;

import com.sun.faces.facelets.compiler.UIInstructions;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
@RequestScoped
public class Util {

    private static final Logger _logger = Logger.getLogger("Util");

    /**
     * printTree might be used within an action of a button
     * As required for an action, it returns a String
     * @return ""
     */
    public String printTree() {
        UIViewRoot root = FacesContext.getCurrentInstance().getViewRoot();
        printTree(root, 0);
        return "";
    }

    private void printTree(UIComponent element, int level) {
        logElement(level, element);
        for (UIComponent child : element.getChildren()) {
            printTree(child, level + 1);
        }
    }

    private void logElement(int level, UIComponent element) {
        String out = "";
        for (int i = 0; i < level; i++) {
            out += "----";
        }
        out += element.getClass().getSimpleName()
                + " - " + element.getFamily()
                + " - " + element.getRendererType() + element;
        _logger.log(Level.INFO, out);
        if (element instanceof UIInstructions){
            UIInstructions instructions = (UIInstructions) element;
            int c = instructions.getChildCount();
        }
    }

}
