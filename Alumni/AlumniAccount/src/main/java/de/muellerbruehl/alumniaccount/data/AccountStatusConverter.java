/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumniaccount.data;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 *
 * @author mmueller
 */
@Converter(autoApply = true)
public class AccountStatusConverter implements AttributeConverter<AccountStatus, String>{

  @Override
  public String convertToDatabaseColumn(AccountStatus status) {
    return status.getKey();
  }

  @Override
  public AccountStatus convertToEntityAttribute(String key) {
    return AccountStatus.fromKey(key);
  }

  
}
