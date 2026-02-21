package com.revpm;

import com.revpm.dao.*;
import com.revpm.util.*;

import java.sql.ResultSet;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        while (true) {

            System.out.println("\n==== PASSWORD MANAGER ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int ch = Integer.parseInt(sc.nextLine());

            if (ch == 1) register();
            else if (ch == 2) login();
            else System.exit(0);
        }
    }

    private static void register() throws Exception {

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Master Password: ");
        String pass = sc.nextLine();

        UserDAO.register(name, email, EncryptionUtil.hash(pass));

        System.out.println("Registered successfully!");
    }

    private static void login() throws Exception {

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        ResultSet rs = UserDAO.find(email);

        if (!rs.next()) {
            System.out.println("User not found");
            return;
        }

        if (!EncryptionUtil.verify(pass, rs.getString("master_password"))) {
            System.out.println("Wrong password");
            return;
        }

        System.out.println("Login successful!");

        dashboard(rs.getInt("id"), rs.getString("master_password"));
    }

    private static void dashboard(int userId, String masterHash) throws Exception {

        while (true) {

            System.out.println("\n==== DASHBOARD ====");
            System.out.println("1. Add Password");
            System.out.println("2. View Vault");
            System.out.println("3. Search Account");
            System.out.println("4. Generate Password");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {

                case 1 -> secure(masterHash, () -> {
                    try {
                        addPassword(userId);
                    } catch (Exception ignored) {}
                });

                case 2 -> secure(masterHash, () -> {
                    try {
                        PasswordDAO.list(userId);
                    } catch (Exception ignored) {}
                });

                case 3 -> {
                    System.out.print("Search: ");
                    PasswordDAO.search(userId, sc.nextLine());
                }

                case 4 -> secure(masterHash, Main::generate);

                case 5 -> { return; }
            }
        }
    }

    private static void secure(String hash, Runnable action) {

        System.out.print("Re-enter master password: ");
        String p = sc.nextLine();

        if (!EncryptionUtil.verify(p, hash)) {
            System.out.println("Wrong master password");
            return;
        }

        if (!InputValidator.strongPassword(p)) {
            System.out.println("Password must be strong!");
            return;
        }

        action.run();
    }

    private static void generate() {
        System.out.print("Length: ");
        int l = Integer.parseInt(sc.nextLine());
        System.out.println("Generated: " + PasswordGenerator.generate(l));
    }

    private static void addPassword(int userId) throws Exception {

        System.out.print("Account: ");
        String acc = sc.nextLine();

        System.out.print("Username: ");
        String u = sc.nextLine();

        System.out.print("Password: ");
        String p = sc.nextLine();

        PasswordDAO.add(userId, acc, u, p);

        System.out.println("Saved securely!");
    }
}