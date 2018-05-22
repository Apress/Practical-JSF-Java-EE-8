/*
 * **************************************************************************
 * This software is created by Michael M??ller.
 * (c) all rights reserved.
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * For any other usage, please contact the author.
 * michael.mueller@mueller-bruehl.de
 * **************************************************************************
 */
package de.muellerbruehl.alumni.gui.component;

import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author mmueller
 */
@FacesComponent(value = "HtmlDynamicTable")
public class HtmlDynamicTable extends UIComponentBase {

  @Override
  public String getFamily() {
    return null;
  }
  
  @Override
  public void encodeAll(FacesContext ctxt) throws IOException{
    Object x = this.getAttributes().get("value");
    if (x != null){
      System.out.println(x.getClass());
    }
    ResponseWriter writer = ctxt.getResponseWriter();
    writer.writeAttribute("id", getClientId(ctxt), null);
    writer.startElement("div", this);
    writer.writeText("This becomes my component", null);
    writer.endElement("div");
  }
  
}
