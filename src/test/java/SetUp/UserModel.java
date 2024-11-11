package SetUp;

public class UserModel {
    String fullname;

    String email;

    String phone;

    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public UserModel(String fullname, String email, String phone, String password){
        this.fullname=fullname;
        this.email=email;
        this.phone=phone;
        this.password=password;
    }
    public UserModel(){

    }
}
