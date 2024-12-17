/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.management.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Aimy
 */
public class UserService {

    private static final String FILE_NAME = "users.txt";

    // Check if a user ID already exists
    public boolean isUserIdExist(String id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(id)) { // Compare the ID field
                    return true;
                }
            }
        }
        return false;
    }

    // Register user after verifying ID uniqueness
    public void registerUser(User user) throws IOException {
        if (isUserIdExist(user.getId())) {
            throw new IllegalArgumentException("ID already exists!");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(user.getId() + "," + user.getName() + "," + user.getPassword() + "," + user.getRole() + "\n");
        }
    }
}
