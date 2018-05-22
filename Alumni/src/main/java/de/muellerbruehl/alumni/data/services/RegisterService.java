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

import de.muellerbruehl.alumni.data.entities.AccountRequest;
import javax.ejb.Stateless;

/**
 *
 * @author mmueller
 */
@Stateless
public class RegisterService extends AbstractService<AccountRequest> {

    public RegisterService() {
        super(AccountRequest.class);
    }

}
