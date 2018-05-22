package de.muellerbruehl.intermezzo;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
@RequestScoped
public class FriendController {

private int _counter;

  public int getCounter() {
    return _counter;
  }

  public List<Friend> getFriends() {
    _counter++;
    return DataProvider.instance.getFriends();
  }

}
