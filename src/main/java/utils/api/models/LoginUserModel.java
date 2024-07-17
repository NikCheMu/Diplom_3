package utils.api.models;

public class LoginUserModel {
    private String email;
    private String password;

    public LoginUserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginUserModel() {
    }

    public String getEmail() {
        return email;
    }

    public LoginUserModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUserModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
