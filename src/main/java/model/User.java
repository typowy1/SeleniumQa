package model;

import enums.Title;
import lombok.Data;

@Data
public class User {

    private Title title;
    private String firstName;
    private String lastName;
    private String password;
    private String days;
    private String months;
    private String years;
    private String newsletterCheckbox;

}
