package Models;

import Utils.DataGenerator;

public class User {

    private String first_name;
    private String second_name;
    private String email;
    private String password;

    public User() {

        this.first_name = DataGenerator.generateFirstName();
        this.second_name = DataGenerator.generateSecondName();
        this.email = DataGenerator.generateEmail();
        this.password = DataGenerator.generateValidPassword();
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
