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
package de.muellerbruehl.alumni.gui.session;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

/**
 *
 * @author mmueller
 */
@SessionScoped
@Named
public class SessionTools implements Serializable {

    private static final Logger LOGGER = Logger.getLogger("SessionTools");

  private String _theme = "standard";

  public String getTheme() {
    return _theme;
  }

  public void setTheme(String theme) {
    _theme = theme;
  }

  public List<String> getThemes() throws IOException {
    String resourcePath = obtainResourcePath();
    List<String> themes = obtainThemes(resourcePath);
    return themes;
  }

  private String obtainResourcePath() {
    ServletContext context = (ServletContext) FacesContext
            .getCurrentInstance()
            .getExternalContext()
            .getContext();
    return context.getRealPath("/resources");
  }

  private List<String> obtainThemes(String resourcePath){
    List<String> themes = new ArrayList<>();
    for (File file : new File(resourcePath).listFiles()) {
      addFilenameIfContainsCss(file, themes);
    }
    return themes;
  }

  private void addFilenameIfContainsCss(File file, List<String> themes) {
    if (!file.isDirectory()) {
      return;
    }
    try (Stream<Path> paths = Files.walk(Paths.get(file.getAbsolutePath()))) {
      boolean conatinsCss = paths
              .filter(Files::isRegularFile)
              .anyMatch(f -> f.toString().toLowerCase().endsWith(".css"));
      if (conatinsCss) {
        themes.add(file.getName());
      }
    } catch (IOException ex) {
      LOGGER.log(Level.SEVERE, null, ex);
    }
  }

  String _language = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale().getLanguage();

  public void setLanguage(String language) {
    _language = language;
  }

  public String getLanguage() {
    return _language;
  }

}
