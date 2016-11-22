package edu.purdue.dbough.wya;


/**
 * Used to display information of where a friend is when
 * user has access to see it
 */

public class User {
    private int profilePicture;
    private long GUID;
    private String name;
    private String distance;
    private boolean viewGpsPermission = false;
    private boolean isOnTheWay = false;

    public User(String name, int profilePicture, String distance) {
        this.profilePicture = profilePicture;
        this.name = name;
        this.distance = distance;
    }

    public long getGUID() {
        return GUID;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public boolean isViewGpsPermission() {
        return viewGpsPermission;
    }

    public void setViewGpsPermission(boolean viewGpsPermission) {
        this.viewGpsPermission = viewGpsPermission;
    }

    public boolean isOnTheWay() {
        return isOnTheWay;
    }

    public void setOnTheWay(boolean onTheWay) {
        isOnTheWay = onTheWay;
    }
}
