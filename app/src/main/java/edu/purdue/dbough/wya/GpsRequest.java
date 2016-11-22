package edu.purdue.dbough.wya;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Holds all the information for a GPS request from a different user.
 * HomeFragment contains a list of these to look through who wants to
 * see where the user is at
 */

public class GpsRequest {
    String requestSender;
    Date timeRequested;
    String requestReason;
    int requesterPicture;

    public GpsRequest(String requestReason, String requestSender, int profilePic, Date timeRequested) {
        this.requestReason = requestReason;
        this.requestSender = requestSender;
        this.timeRequested = timeRequested;
        this.requesterPicture = profilePic;
    }

    public int getRequesterPicture() {
        return requesterPicture;
    }

    public void setRequesterPicture(int requesterPicture) {
        this.requesterPicture = requesterPicture;
    }

    public String getRequestReason() {
        return requestReason;
    }

    public Date getTimeRequested() {
        return timeRequested;
    }

    public String getTimeRequestedString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

    public String getRequestSender() {
        return requestSender;
    }
}
