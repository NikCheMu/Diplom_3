package utils.api.models;

public class CreateUserModel {
    private String email;
    private String password;
    private String name;

    public CreateUserModel(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public CreateUserModel() {
    }

    public String getEmail() {
        return email;
    }

    public CreateUserModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateUserModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateUserModel setName(String name) {
        this.name = name;
        return this;
    }
}
