package week6task.ecommercesite2.entity;

public class User {
//    create fields to correspond with user table in the database
    private String email;
    private String name;
    private String password;
    private String is_admin;

//    no args constructor
    public User() {
    }

//    all args constructor
    public User(String email, String name, String password, String is_admin) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.is_admin = is_admin;
    }

//    setters and getters for fields
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

//    override to string method
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", is_admin='" + is_admin + '\'' +
                '}';
    }
}
