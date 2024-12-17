/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.management.system;

/**
 *
 * @author Aimy
 */
public class User {
    private String id;
    private String name;
    private String password;
    private String role;

    public User(String id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password; 
    }

    public String getRole() {
        return role;
    }

    public boolean isStudent() {
        return "student".equalsIgnoreCase(role);
    }

    public boolean isLecturer() {
        return "lecturer".equalsIgnoreCase(role);
    }
}
