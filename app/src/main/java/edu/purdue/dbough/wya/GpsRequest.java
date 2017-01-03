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
    String dayOfRequest;
    String requestTime;
    int requesterPicture;

    public GpsRequest(String requestTime, String requestSender, int profilePic, String dayOfRequest) {
        this.requestTime = requestTime;
        this.requestSender = requestSender;
        this.dayOfRequest = dayOfRequest;
        this.requesterPicture = profilePic;
    }

    public int getRequesterPicture() {
        return requesterPicture;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public String getDayRequested() {
        return dayOfRequest;
    }

    public String getRequestSender() {
        return requestSender;
    }
}
