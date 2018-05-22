/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumni.data.mail;

import de.muellerbruehl.alumni.data.services.AbstractService;
import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author mmueller
 */
@RequestScoped
@Transactional
public class MailService extends AbstractService {

  public MailTemplate findTemplateByName(TemplateName name) {
    String jpql = "select mt from MailTemplate mt where mt._name = :name";
    TypedQuery<MailTemplate> query = getEntityManager().createQuery(jpql, MailTemplate.class);
    query.setParameter("name", name.name());
    try {
      return query.getSingleResult();
    } catch (Exception ex) {
      return makeNewTemplate(name);
    }
  }

  //public MailTemplate findTemplateByName(String name, String langCode){
  //}
  private MailTemplate makeNewTemplate(TemplateName name) {
    // until the template editor is ready, this method creates hard coded mail templates
    switch (name) {
      case ActivationMail:
        return makeActivationMail();
      default:
        return new MailTemplate();
    }
  }

  private MailTemplate makeActivationMail() {
    String subject = "Activate Alumni account";
    String body = "Hello {firstName},\r\n\r\n"
            + "In order to complete your registration, "
            + "please click onto the following link {link}";
    MailTemplate mailTemplate = new MailTemplate();
    mailTemplate.setName(TemplateName.ActivationMail.name());
    mailTemplate.setSubject(subject);
    mailTemplate.setBody(body);
    return save(mailTemplate);
  }
}
