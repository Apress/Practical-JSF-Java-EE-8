/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumni.data.services;

import de.muellerbruehl.alumni.data.dto.Address;
import de.muellerbruehl.alumni.data.entities.College;
import de.muellerbruehl.alumni.data.entities.Country;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author mmueller
 */
@ApplicationScoped
@Transactional
public class CollegeService extends AbstractService {

  private final Map<String, List<Country>> _countries = new HashMap<>();

  public List<Country> findAllCountries(String langCode) {
    if (!_countries.containsKey(langCode)) {
      String jpql = "select c from Country c where c._langCode = :langCode";
      TypedQuery<Country> query = getEntityManager().createQuery(jpql, Country.class);
      query.setParameter("langCode", langCode);
      List<Country> countries = query.getResultList();
      _countries.put(langCode, countries);
    }
    return _countries.get(langCode);
  }

  public List<College> findCollegesByPostalCode(Address address) {
      String jpql = "select c from College c where c._countryCode = :countryCode and c._postalCode = :postalCode";
      TypedQuery<College> query = getEntityManager().createQuery(jpql, College.class);
      query.setParameter("countryCode", address.getCountryCode());
      query.setParameter("postalCode", address.getPostalCode());
      return query.getResultList();
  }

  public List<College> findCollegesByAdress(Address address) {
      String jpql = "select c from College c where c._countryCode = :countryCode and c._postalCode = :postalCode and c._town = :town and c._street = :street";
      TypedQuery<College> query = getEntityManager().createQuery(jpql, College.class);
      query.setParameter("countryCode", address.getCountryCode());
      query.setParameter("postalCode", address.getPostalCode());
      query.setParameter("town", address.getTown());
      query.setParameter("street", address.getStreet());
      return query.getResultList();
  }

  public College findCollege(byte[] id) {
    return find(College.class, id);
  }
}
