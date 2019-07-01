import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class JDBCUtils {
	public static Connection getConnection(){
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/caddey","root", "");
			System.out.print("Database is connected !");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Do not connect to DB - Error:"+e);
		}
		return conn;
	}
	
	public static void free(ResultSet rs, Statement sta , Connection conn){
		try {
			if(rs != null){
				rs.close();
				rs = null ;
			}
			
			if(sta != null){
				sta.close();
				sta = null ;
			}
			
			if(conn != null){
				conn.close();
				conn = null ;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}