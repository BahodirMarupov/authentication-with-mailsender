package uz.themind.springauthentication.model.request;

import uz.themind.springauthentication.model.domen.UserPrincipal;
import uz.themind.springauthentication.model.enums.UserRole;

import javax.validation.constraints.*;

public class RegistrationRequest {

    @NotEmpty(message = "Name cannot be blank!")
    @Size(min = 3, max = 16, message = "Invalid name!")
    private String name;

    @NotEmpty(message = "Username cannot be blank!")
    private String username;
    @NotNull
    @Email(message = "Invalid email!")
    private String email;
    @NotEmpty(message = "Password cannot be blank!")
    @Pattern(regexp = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20})", message = "Invalid password!")
    private String password;
    @NotEmpty(message = "Confirmation password cannot be blank!")
    private String prePassword;

    public RegistrationRequest() {
    }

    public RegistrationRequest(@NotEmpty(message = "Name cannot be blank!") @Size(min = 3, max = 16, message = "Invalid name!") String name, @NotEmpty(message = "Username cannot be blank!") String username, @NotNull @Email(message = "Invalid email!") String email, @NotEmpty(message = "Password cannot be blank!") @Pattern(regexp = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20})", message = "Invalid password!") String password, @NotEmpty(message = "Confirmation password cannot be blank!") String prePassword) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.prePassword = prePassword;
    }

    public UserPrincipal mapToUserPrincipal(UserRole userRole, String encodedPassword, boolean locked, boolean enabled) {
        return new UserPrincipal(this.name, this.username, this.email, encodedPassword, userRole, locked, enabled);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPrePassword() {
        return prePassword;
    }

    public void setPrePassword(String prePassword) {
        this.prePassword = prePassword;
    }
}
