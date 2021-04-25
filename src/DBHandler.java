import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
public class DBHandler {

	//Write the required business logic as expected in the question description
	public Connection establishConnection() {

		//fill the code
		
		Properties p = new Properties();
		Connection con = null;
		try {
			FileInputStream f = new FileInputStream("db.properties");
			p.load(f);
			Class.forName(p.getProperty("db.classname"));
			String url = p.getProperty("db.url");
			String username = p.getProperty("db.username");
			String password = p.getProperty("db.password");
			
			con = DriverManager.getConnection(url,username,password);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;

	}
}
