package de.muellerbruehl.alumni.gui.util.converter;

import de.muellerbruehl.alumni.data.entities.College;
import de.muellerbruehl.alumni.data.services.CollegeService;
import de.muellerbruehl.alumni.gui.util.StringUtils;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("collegeConverter")
public class CollegeConverter implements Converter{

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    CollegeService service = (CollegeService) context.getExternalContext().getApplicationMap().get("collegeService");
    return service.findCollege(StringUtils.hex2byte(value));
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    College college = (College) value;
    return StringUtils.byte2hex(college.getId());
  }
  
}
