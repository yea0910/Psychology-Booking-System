/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.management.system;

import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Aimy
 */
public class LoginService {

    private static final String FILE_PATH = "users.txt";

    public User validateLogin(String id, String password) {
        try (FileReader fr = new FileReader(FILE_PATH); Scanner reader = new Scanner(fr)) {
            reader.useDelimiter("[,\n]");

            while (reader.hasNext()) {
                String userId = reader.next().trim();
                String userName = reader.next().trim();
                String userPassword = reader.next().trim();
                String userRole = reader.next().trim();

                if (userId.equals(id) && userPassword.equals(password)) {
                    return new User(userId, userName, userPassword, userRole);
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading users file: " + e.getMessage());
        }
        return null;
    }

    public static User validateLoginByRole(String id, String password, String role) {
        LoginService service = new LoginService();
        User user = service.validateLogin(id, password);
        if (user != null && role.equalsIgnoreCase(user.getRole())) {
            return user;
        }
        return null;
    }

}
