/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumni.gui.classroom;

import de.muellerbruehl.alumni.business.dto.Account;
import de.muellerbruehl.alumni.data.services.ChatService;
import de.muellerbruehl.alumni.gui.user.UserController;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author mmueller
 */
@ServerEndpoint("/classroomchat")
public class ClassroomChat {

  private static final Map<byte[], Set<Session>> PEERS = new ConcurrentHashMap<>();
  private static final Logger LOGGER = Logger.getLogger(ClassroomChat.class.getName());

  private ChatService _chatService;
  private Account _account;
  private byte[] _finalYearId;

  @Inject
  public ClassroomChat(UserController user, ChatService chatService) {
    _account = user.getAccount();
    _finalYearId = user.getFinalYearId();
    _chatService = chatService;
  }
  
  @PostConstruct
  private void init(){

  }

  public ClassroomChat() {
    LOGGER.log(Level.INFO, "ctor ClassroomChat");
  }

  @OnMessage
  public void onMessage(String message, Session session) {
    _chatService.saveChatEntry(_finalYearId, _account.getId(), message);
    //String name = session.getUserPrincipal().getName();
    for (Session peer : PEERS.get(_finalYearId)) {
      try {
        peer.getBasicRemote().sendObject(_account.getDisplayName() + ": " + message);
      } catch (IOException | EncodeException ex) {
        // in case of error, log problem and continue
        LOGGER.log(Level.SEVERE, null, ex);
      }
    }
  }

  @OnOpen
  public void onOpen(Session peer) {
    LOGGER.log(Level.INFO, "onOpen ClassroomChat, user {0}", _account.getDisplayName());
    if (!PEERS.containsKey(_finalYearId)) {
      PEERS.put(_finalYearId, Collections.synchronizedSet(new HashSet<>()));
    }
    PEERS.get(_finalYearId).add(peer);
    sendLatestMessages(peer);
  }

  private void sendLatestMessages(Session peer) {
    List<String> messages = _chatService.getLatestMessages(_finalYearId);
    for (String message : messages) {
      try {
        peer.getBasicRemote().sendObject(message);
      } catch (IOException | EncodeException ex) {
        // in case of error, log problem and continue
        LOGGER.log(Level.SEVERE, null, ex);
      }

    }
  }

  @OnClose
  public void onClose(Session peer) {
    LOGGER.log(Level.INFO, "onClose ClassroomChat, user {0}", _account.getDisplayName());
    PEERS.get(_finalYearId).remove(peer);
  }

}
