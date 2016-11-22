package edu.purdue.dbough.wya;


import com.mikhaellopez.circularimageview.CircularImageView;

/**
 * Used to display information of where a friend is when
 * user has access to see it
 */

public class User {
    private CircularImageView profilePicture;
    private int uncroppedProfilePicture;
    private long GUID;
    private String name;
    private String distance;
    private boolean viewGpsPermission = false;
    private boolean isOnTheWay = false;

    public User(String distance, String name) {
        this.distance = distance;
        this.name = name;
        //TODO include default user profile picture
    }

    public User(String name, int uncroppedProfilePicture, String distance) {
        this.uncroppedProfilePicture = uncroppedProfilePicture;
        this.name = name;
        this.distance = distance;
    }

    public long getGUID() {
        return GUID;
    }

    public int getUncroppedProfilePicture() {
        return uncroppedProfilePicture;
    }

    public void setUncroppedProfilePicture(int uncroppedProfilePicture) {
        this.uncroppedProfilePicture = uncroppedProfilePicture;
    }

    public CircularImageView getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(CircularImageView profilePicture) {
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
