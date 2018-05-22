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
package de.muellerbruehl.alumni.gui.college;

import de.muellerbruehl.alumni.data.dto.Address;
import de.muellerbruehl.alumni.data.entities.College;
import de.muellerbruehl.alumni.data.entities.Country;
import de.muellerbruehl.alumni.data.services.CollegeService;
import de.muellerbruehl.alumni.gui.session.SessionTools;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author mmueller
 */
@Named
@ViewScoped
public class FinalClass implements Serializable {

  private static final long serialVersionUID = 1L;

  private final SessionTools _sessionTools;
  private final CollegeService _collegeService;

  @Inject
  public FinalClass(SessionTools sessionTools, CollegeService collegeService) {
    _sessionTools = sessionTools;
    _collegeService = collegeService;
  }

  @PostConstruct
  private void init(){
    _address.setCountryCode("DE");
  }
  
  private Address _address = new Address();

  public Address getAddress() {
    return _address;
  }

  public void setAddress(Address address) {
    _address = address;
  }

  public List<Country> getCountries() {
    String langCode = _sessionTools.getLanguage();
    return _collegeService.findAllCountries(langCode);
  }

  public void handleSelect(SelectEvent event) {
    String countryCode = (String) event.getObject();
    _address.setCountryCode(countryCode);
  }

  public List<Country> completeCountry(String part) {
    String start = part.toLowerCase();
    return getCountries()
            .stream()
            .filter(c -> c.getName().toLowerCase().startsWith(start))
            .collect(Collectors.toList());
  }

  private String _name;

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  public List<College> getCollegesByPostalCode() {
    return _collegeService.findCollegesByPostalCode(_address);
  }

  public List<College> getCollegesByAdress() {
    return _collegeService.findCollegesByAdress(_address);
  }
}
