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
package de.muellerbruehl.alumni.gui.util;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

/**
 *
 * @author mmueller
 */
public class FakeEndpoint extends Endpoint {

  @Override
  public void onOpen(Session session, EndpointConfig config) {
    // https://java.net/jira/browse/WEBSOCKET_SPEC-240
  }
}
