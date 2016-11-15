package edu.purdue.dbough.wya;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Holds all the information for a GPS request from a different user
 */

public class GpsRequest {
    String requestSender;
    Date timeRequested;
    String requestReason;

    public GpsRequest(String requestReason, String requestSender, Date timeRequested) {
        this.requestReason = requestReason;
        this.requestSender = requestSender;
        this.timeRequested = timeRequested;
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
