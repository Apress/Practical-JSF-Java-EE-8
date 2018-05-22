/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumnirealm;

import com.sun.appserv.security.AppservRealm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author mmueller
 */
public class AlumniRealm extends AppservRealm {

  @Override
  public String getAuthType() {
    return "alumniRealm";
  }

  @Override
  public String getJAASContext() {
    return "alumniRealm";
  }

  @Override
  public Enumeration getGroupNames(String username) {
    List<String> groups = new ArrayList<>();
    groups.add("member");
    groups.add("admin");
    return Collections.enumeration(groups);
  }

}
