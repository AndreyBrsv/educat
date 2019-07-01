package ink.educat.user.api.Entities;

import org.springframework.lang.Nullable;

import java.io.Serializable;

public class User implements Serializable {

    private long id;

    private String login;

    @Nullable
    private String email;

    @Nullable
    private String firstName;

    @Nullable
    private String secondName;

    public User() {

    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Nullable String firstName) {
        this.firstName = firstName;
    }

    @Nullable
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(@Nullable String secondName) {
        this.secondName = secondName;
    }
}
