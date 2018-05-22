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
package de.muellerbruehl.alumni.login;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.SessionMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mmueller
 */
@Named
@RequestScoped
public class Login {
    private static final Logger _logger = Logger.getLogger("Login");

    //@Inject private AccountFacade _accountFacade;
    private String _email = "Test";
    private String _userName = "Test";
    private String _password;

    // <editor-fold defaultstate="collapsed" desc="getter / setter">
    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }
    // </editor-fold>    

    @Inject @SessionMap Map<String, Object> _sessionMap;
    public String login() {
        //Account account = _accountFacade.find(1);

        FacesContext context = FacesContext.getCurrentInstance();
      Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
      System.out.println(sessionMap.size());
      System.out.println(_sessionMap);
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            String hash = getHash("SHA-256", _password);
            request.login(_userName, _password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed."));
            return "/loginError";
        } catch (NoSuchAlgorithmException ex) {
            _logger.log(Level.SEVERE, null, ex);
        }
        return "/user/index";
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
      System.out.println("");
    }

    private String getHash(String algorithm, String input) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            return byte2hex(md.digest(input.getBytes("utf-8")));
        } catch (UnsupportedEncodingException ex) {
            _logger.log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private String byte2hex(byte[] source){
        StringBuilder sb = new StringBuilder();
        for(byte b : source){
            String hex = Integer.toHexString(b+128);
            if (hex.length() == 1){
                sb.append("0");
            }
            sb.append(hex);
        }
        return sb.toString();
    }
/*    
try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String text = "admin";
            md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);

            System.out.println(output);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(PasswordTest.class.getName()).log(Level.SEVERE, null, ex);

        }
  */  
}
