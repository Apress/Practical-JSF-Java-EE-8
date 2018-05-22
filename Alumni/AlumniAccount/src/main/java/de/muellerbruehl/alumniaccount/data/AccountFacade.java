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
package de.muellerbruehl.alumniaccount.data;

import de.muellerbruehl.alumniaccount.util.HashUtils;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author mmueller
 */
@ApplicationScoped
@Transactional
public class AccountFacade extends AbstractService {

  private static AccountFacade INSTANCE;
  private static final Logger LOGGER = Logger.getLogger("AccountService");

  public String retrieveRoles(String id) {
    // todo:retrieve all roles
    return "member";
  }

  public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
    // this observer will be called on startup and thus create an eager instance of this bean
  }

  public AccountFacade() {
    INSTANCE = this;
  }

  public static AccountFacade getInstance() {
    return INSTANCE;
  }

  public Account saveAccount(Account account) {
    return save(account);
  }

  public List<Account> findAllAccounts() {
    return findAll(Account.class);
  }

  public Account findAccount(String id) {
    return findAccount(HashUtils.hex2byte(id));
  }

  public Account findAccount(byte[] id) {
    return find(Account.class, id);
  }

  public Account findAccountByName(String loginName) {
    String jpql = "select a from Account a where a._loginName = :loginName";
    TypedQuery<Account> query = getEntityManager().createQuery(jpql, Account.class);
    query.setParameter("loginName", loginName);
    try {
      return query.getSingleResult();
    } catch (Exception ex) {
      throw new IllegalArgumentException("Unknown user name: " + loginName);
    }
  }

  public Account findAccountByEmail(String email) {
    String jpql = "select a from Account a where a._email = :email";
    TypedQuery<Account> query = getEntityManager().createQuery(jpql, Account.class);
    query.setParameter("email", email);
    try {
      return query.getSingleResult();
    } catch (Exception ex) {
      throw new IllegalArgumentException("Unknown email: " + email);
    }
  }

  private Account findAccountByAccessKey(String accessKey) {
    String jpql = "select a from Account a where a._accessKey = :accessKey";
    TypedQuery<Account> query = getEntityManager().createQuery(jpql, Account.class);
    query.setParameter("accessKey", HashUtils.hex2byte(accessKey));
    try {
      return query.getSingleResult();
    } catch (Exception ex) {
      throw new IllegalArgumentException("Unknown accessKey: " + accessKey);
    }
  }

  public Account createAccount(Account account) {
    if (emailExists(account.getEmail())) {
      throw new IllegalArgumentException("createAccount, email exists: " 
              + account.getEmail());
    }
    account.setLoginName(deriveUniqueLoginName(account));
    account.setStatus(AccountStatus.New);
    return save(account);
  }

  private boolean emailExists(String email) {
    String jpql = "select count(a) from Account a where a._email = :email";
    TypedQuery<Long> query = getEntityManager().createQuery(jpql, Long.class);
    query.setParameter("email", email);
    long count = query.getSingleResult();
    return count > 0;
  }

  public String deriveUniqueLoginName(Account account) {
    String loginName = account.getLoginName();
    if (loginName.isEmpty()){
      loginName = account.getFirstName() + "." + account.getLastName();
    }
    if (!loginNameExists(loginName)) {
      return loginName;
    }
    return makeUniqueName(loginName);
  }

  private boolean loginNameExists(String loginName) {
    String jpql = "select count(a) from Account a where a._loginName = :loginName";
    TypedQuery<Long> query = getEntityManager().createQuery(jpql, Long.class);
    query.setParameter("loginName", loginName);
    long count = query.getSingleResult();
    return count > 0;
  }

  private String makeUniqueName(String loginName) {
    List<String> loginNames = getSimilarExistingNames(loginName);
    Random rand = new Random();
    for (int run = 1; run < 1000; run++) {
      String newName = loginName + rand.nextInt(run * 100);
      if (!loginNames.contains(newName)) {
        return newName;
      }
    }
    return loginName + UUID.randomUUID();
  }

  private List<String> getSimilarExistingNames(String loginName) {
    String jpql = "select a._loginName from Account a where a._loginName like :loginName";
    TypedQuery<String> query = getEntityManager().createQuery(jpql, String.class);
    query.setParameter("loginName", loginName + "%");
    List<String> loginNames = query.getResultList();
    return loginNames;
  }

  public boolean enableAccount(String accessKey) {
    try{
      Account account = findAccountByAccessKey(accessKey);
      account.setStatus(AccountStatus.Active);
      save(account);
      return true;
    }catch(Exception ex){
      LOGGER.log(Level.WARNING, ex.getMessage());
      return false;
    }
  }

  public void deleteAccount(String id) {
    String jpql = "DELETE FROM Account a WHERE a._id = :id";
    Query query = getEntityManager().createQuery(jpql);
    query.setParameter("id", HashUtils.hex2byte(id));
    query.executeUpdate();
    clearCache();
  }

  public void deleteOldAccountRequests() {
    String jpql = "DELETE FROM Account a "
            + "WHERE a._created < :date and a._status = :status";
    Query query = getEntityManager().createQuery(jpql);
    query.setParameter("date", getDateWithDayOffset(-1));
    //query.setParameter("date", LocalDateTime.now().minusDays(1));
    query.setParameter("status", AccountStatus.New);
    query.executeUpdate();
  }

  public Date getDateWithDayOffset(int offset) {
    return new Date(System.currentTimeMillis()+ offset*24*60*60*1000);
  }

}
