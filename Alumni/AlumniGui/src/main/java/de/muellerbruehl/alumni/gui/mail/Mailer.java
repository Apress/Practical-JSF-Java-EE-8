/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumni.gui.mail;

import de.muellerbruehl.alumni.data.mail.MailService;
import de.muellerbruehl.alumni.data.mail.MailTemplate;
import de.muellerbruehl.alumni.data.mail.TemplateName;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author muellermi
 */
@Dependent
public class Mailer implements Serializable {

  protected static final Logger LOGGER = Logger.getLogger("Mailer");

  @Resource(lookup = "alumniMail")
  private Session _session;

  public boolean sendMail(String to, String subject, String body) {
    List<Recipient> recipients = new ArrayList<>();
    recipients.add(new Recipient(to, RecipientType.TO));
    return sendMail(recipients, subject, body);
  }

  public boolean sendMail(List<Recipient> recipients,
          String subject, String body, String... files) {
    try {
      MimeMessage message = composeMessage(recipients,
              subject, body, files);
      String user = _session.getProperty("mail.user");
      String password = _session.getProperty("mail.smpt.password");
      Transport.send(message, user, password);
      return true;
    } catch (MessagingException ex) {
      LOGGER.log(Level.SEVERE, "Mailer failed: {0}", ex.getMessage());
      return false;
    }
  }

  private MimeMessage composeMessage(List<Recipient> recipients,
          String subject,
          String body,
          String[] files) throws MessagingException {
    MimeMessage message = new MimeMessage(_session);
    for (Recipient recipient : recipients) {
      message.addRecipient(recipient.getType(),
              new InternetAddress(recipient.getEmail()));
    }
    message.setSubject(subject);
    message.setContent(getMultipartBody(body, files));
    String from = _session.getProperty("mail.from");
    message.setFrom(new InternetAddress(from));
    return message;
  }

  private Multipart getMultipartBody(String body, String[] files)
          throws MessagingException {
    MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText(body);
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);
    for (String file : files) {
      addAttachment(multipart, file);
    }
    return multipart;
  }

  private static void addAttachment(Multipart multipart, String filename)
          throws MessagingException {
    if (filename.isEmpty()) {
      return;
    }
    MimeBodyPart messageBodyPart = new MimeBodyPart();
    DataSource source = new FileDataSource(filename);
    messageBodyPart.setDataHandler(new DataHandler(source));
    File file = new File(filename);
    messageBodyPart.setFileName(file.getName());
    multipart.addBodyPart(messageBodyPart);
  }

  @Inject private MailService _mailService;

  public MailTemplate findTemplateByName(TemplateName templateName) {
    return _mailService.findTemplateByName(templateName);
  }

}
