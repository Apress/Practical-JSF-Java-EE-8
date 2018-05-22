/*
 * **************************************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved.
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * For any other usage, please contact the author.
 * michael.mueller@mueller-bruehl.de
 * **************************************************************************
 */
package de.muellerbruehl.alumni.data.services;

import de.muellerbruehl.alumni.data.entities.ChatEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author mmueller
 */
@ApplicationScoped
@Transactional
//@Stateless
public class ChatService extends AbstractService {

  public ChatEntry saveChatEntry(ChatEntry chatEntry) {
    return save(chatEntry);
  }

  public ChatEntry saveChatEntry(byte[] finalClassId, byte[] accountId, String message) {
    ChatEntry chatEntry = new ChatEntry(finalClassId, accountId, message);
    return save(chatEntry);
  }

  /**
   * retrieves latest 10 messages which are not older than 2 hours
   *
   * @param finalYearId
   * @return
   */
  public List<String> getLatestMessages(byte[] finalYearId) {
    String jpql = "Select concat(a._firstName, ' ', a._lastName, ': ', e._message) "
            + "from ChatEntry e "
            + "join Account a "
            + "where e._accountId = a._id "
            + "and e._finalYearId = :finalYearId "
            + "and e._moment > :refMoment order by e._id desc";
    TypedQuery<String> query = getEntityManager().createQuery(jpql, String.class);
    query.setParameter("finalYearId", finalYearId);
    query.setMaxResults(10);
    Date refMoment = new Date(new Date().getTime() - 2 * 3600 * 1000);
    query.setParameter("refMoment", refMoment);
    List<String> messages = query.getResultList();
    List<String> lastMessages = new ArrayList<>();
    for (String message : messages){
      lastMessages.add(0, message);
    }
    return lastMessages;
  }

}
