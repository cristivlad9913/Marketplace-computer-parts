package fmi.cloudcomputing.buyerservice.user.presentation;

//A DTO ( Data Transfer Object) is what we send/receive to/from the frontend
//Basically we filter what fields we do not want from the @Entity class
public class CreateUserDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
