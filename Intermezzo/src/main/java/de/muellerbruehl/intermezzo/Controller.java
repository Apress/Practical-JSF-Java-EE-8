package de.muellerbruehl.intermezzo;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
@RequestScoped
public class Controller {

  private final List<Friend> _friends = new ArrayList<>();

  public Controller() {
    _friends.add(new Friend("Sally"));
    _friends.add(new Friend("Bob"));
    _friends.add(new Friend("John"));
  }

  public List<Friend> getFriends() {
    return _friends;
  }

}
