/*
 * ***********************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved.
 *
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * ***********************************************************
 */
package de.muellerbruehl.books.helper;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author muellermi
 */
public class Topic implements Serializable {

  private final String _key;
  private final String _title;
  private final String _outcome;
  private final String _info;
  private final String _imageEnabled;
  private final String _imageDisabled;
  private boolean _isEnabled;

  /**
   * A topic represents an item to be shown in an editor.
   *
   * @param key to identify this topic
   * @param title
   * @param outcome (URL)
   * @param info Optional info conveyed by this topic, e.g. locale
   * @param imageEnabled
   * @param imageDisabled
   * @param isEnabled Flag to select one of the images
   */
  private Topic(String key, String title, String outcome, String info, String imageEnabled, String imageDisabled, boolean isEnabled) {
    _key = key;
    _title = title;
    _outcome = outcome;
    _info = info;
    _imageEnabled = imageEnabled;
    _imageDisabled = imageDisabled;
    _isEnabled = isEnabled;
  }

  // <editor-fold defaultstate="collapsed" desc="getter / setter Definition">
  public String getKey() {
    return _key;
  }

  public String getTitle() {
    return _title;
  }

  public String getOutcome() {
    return _outcome;
  }

  public String getInfo() {
    return _info;
  }

  public String getImageEnabled() {
    return _imageEnabled;
  }

  public String getImageDisabled() {
    return _imageDisabled;
  }

  public String getImage() {
    return _isEnabled ? _imageEnabled : _imageDisabled;
  }

  public boolean isIsEnabled() {
    return _isEnabled;
  }

  public void setIsEnabled(boolean isEnabled) {
    _isEnabled = isEnabled;
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="hashCode / equals">
  @Override
  public int hashCode() {
    return Objects.hashCode(_key);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Topic other = (Topic) obj;
    return _key.equals(other._key);
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="TopicBuilder">
  public static class TopicBuilder {

    private final String _key;
    private String _title = "";
    private String _outcome = "";
    private String _info = "";
    private String _imageEnabled = "";
    private String _imageDisabled = "";
    private boolean _isEnabled = true;

    static public TopicBuilder createBuilder(String key) {
      return new TopicBuilder(key);
    }

    private TopicBuilder(String key) {
      _key = key;
      _title = key;   // defaults to key
    }

    public TopicBuilder setTitle(String title) {
      _title = title;
      return this;
    }

    public TopicBuilder setOutcome(String outcome) {
      _outcome = outcome;
      return this;
    }

    public TopicBuilder setInfo(String info) {
      _info = info;
      return this;
    }

    public TopicBuilder setImageEnabled(String imageEnabled) {
      _imageEnabled = imageEnabled;
      return this;
    }

    public TopicBuilder setImageDisabled(String imageDisabled) {
      _imageDisabled = imageDisabled;
      return this;
    }

    public TopicBuilder setEnsabled(boolean enabled) {
      _isEnabled = enabled;
      return this;
    }

    public Topic build() {
      return new Topic(_key, _title, _outcome, _info, _imageEnabled, _imageDisabled, _isEnabled);
    }
// </editor-fold>
  }
}
