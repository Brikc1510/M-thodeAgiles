/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {

    private static Connection connection = null;
    private static String USERNAME = "root";
    private static String PASSWORD = "1234";
    private static String URL = "jdbc:mysql://localhost/gestionbudgetdb";

    private DB_Connection() {
    }

    public static Connection get_connection() {

        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connection OK");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return connection;
    }
}
