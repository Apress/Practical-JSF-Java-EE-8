/*
 * ***********************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved.
 *
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * ***********************************************************
 */
package de.muellerbruehl.alumniaccount.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author mmueller
 */
public abstract class AbstractService {

  @PersistenceContext(unitName = "AlumniPU")
  private EntityManager _em;

//  @PostConstruct
//  private void init() {
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("AlumniPU");
//    _em = emf.createEntityManager();
//  }
  protected EntityManager getEntityManager() {
    return _em;
  }

  protected <T> T read(Object id, Class<T> entityClass) {
    return _em.find(entityClass, id);
  }

  protected <T> T save(T entity) {
    T merge = _em.merge(entity);
    return merge;
  }

  protected void delete(Object entity) {
    if (isAttached(entity)) {
      _em.remove(entity);
    } else {
      _em.remove(_em.merge(entity));
    }
  }

  protected <T> T find(Class<T> entityClass, Object id) {
    return _em.find(entityClass, id);
  }

  protected <T> T findFresh(Class<T> entityClass, Object id) {
    Map<String, Object> hints = new HashMap<>();
    hints.put("javax.persistence.cache.retrieveMode", "BYPASS");
    return _em.find(entityClass, id, hints);
  }

  protected <T> List<T> findAll(Class<T> entityClass) {
    CriteriaQuery cq = _em.getCriteriaBuilder().createQuery();
    cq.select(cq.from(entityClass));
    return _em.createQuery(cq).getResultList();
  }

  protected <T> List<T> findRange(Class<T> entityClass, int[] range) {
    return findRange(entityClass, range[0], range[1]);
  }

  protected <T> List<T> findRange(Class<T> entityClass, int from, int to) {
    CriteriaQuery cq = _em.getCriteriaBuilder().createQuery();
    cq.select(cq.from(entityClass));
    javax.persistence.Query q = _em.createQuery(cq);
    q.setMaxResults(to - from + 1);
    q.setFirstResult(from);
    return q.getResultList();
  }

  protected <T> int count(Class<T> entityClass) {
    CriteriaQuery cq = _em.getCriteriaBuilder().createQuery();
    Root<T> rt = cq.from(entityClass);
    cq.select(_em.getCriteriaBuilder().count(rt));
    javax.persistence.Query q = _em.createQuery(cq);
    return ((Long) q.getSingleResult()).intValue();
  }

  protected boolean isAttached(Object entity) {
    return _em.contains(entity);
  }

  protected void clearCache() {
    _em.flush();
    _em.getEntityManagerFactory().getCache().evictAll();
  }

}
