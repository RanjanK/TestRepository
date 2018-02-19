package model;

/**
 * Created by ranjankulkarni on 22/01/18.
 */

public class User {
    public long id;
    public String firstname;
    public String lastname;
    public boolean isFollowing;

    public User() {
    }

    public User(APIUser apiUser) {
        this.id = apiUser.id;
        this.firstname = apiUser.firstname;
        this.lastname = apiUser.lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", isFollowing=" + isFollowing +
                '}';
    }
}