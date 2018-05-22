package de.muellerbruehl.books.helper;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mmueller
 */


public class TopicTest {
    
  @Test
  public void testTitleShallDefaultToKey() {
    Topic topic = Topic.TopicBuilder.createBuilder("xxx").build();
    assertEquals("xxx", topic.getTitle());
  }
  
  @Test
  public void testBuildInfo() {
    Topic topic = Topic.TopicBuilder.createBuilder("xxx").setInfo("info").build();
    assertEquals("info", topic.getInfo());
  }
  
}
