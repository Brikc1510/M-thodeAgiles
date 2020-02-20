/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
	
	Connection connection=null;
	public Connection get_connection() {
		
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/applicationagile","root","");
			System.out.println("Connection OK");
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}
}
