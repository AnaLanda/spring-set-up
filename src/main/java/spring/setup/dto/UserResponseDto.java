package spring.setup.dto;

public class UserResponseDto {
    private String name;
    private String surname;
    private String email;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserResponseDto: "
                + "name = " + name + "; "
                + "surname = " + surname + "; "
                + "email = " + email;
    }
}
