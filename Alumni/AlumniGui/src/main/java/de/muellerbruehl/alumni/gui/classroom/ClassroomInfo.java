/*
 * **************************************************************************
 * This software is created by Michael M??ller.
 * (c) all rights reserved.
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * For any other usage, please contact the author.
 * michael.mueller@mueller-bruehl.de
 * **************************************************************************
 */
package de.muellerbruehl.alumni.gui.classroom;

import de.muellerbruehl.alumni.business.dto.Account;
import de.muellerbruehl.alumni.data.entities.FinalYear;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
public class ClassroomInfo {
    private Account _account;

    public Account getAccount() {
        return _account;
    }

    public void setAccount(Account account) {
        this._account = account;
    }

    private FinalYear _finalYear;

    public FinalYear getFinalYear() {
        return _finalYear;
    }

    public void setFinalYear(FinalYear finalYear) {
        this._finalYear = finalYear;
    }

    private int lastSeenMessage;

    public int getLastSeenMessage() {
        return lastSeenMessage;
    }

    public void setLastSeenMessage(int lastSeenMessage) {
        this.lastSeenMessage = lastSeenMessage;
    }
}
