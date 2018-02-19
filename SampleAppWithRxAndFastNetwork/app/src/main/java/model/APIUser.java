package model;

/**
 * Created by ranjankulkarni on 22/01/18.
 */

public class APIUser {

    int id;
    String firstname;
    String lastname;

    @Override
    public String toString() {
        return "ApiUser{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
