import java.util.*;
// admin class
class Admin {
    private String name;
    private String email;
    private String password;

    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
public class Library {
    private List<Admin> admins;

    public Library() {
        this.admins = new ArrayList<>();
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public Admin getAdminByEmail(String email) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email)) {
                return admin;
            }
        }
        return null; // Admin not found
    }
}
//student login class
class Student {
    private String name;
    private String email;
    private String password;

    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
