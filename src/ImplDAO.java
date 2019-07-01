import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class ImplDAO implements IDAO{

	@Override
	public void addProductToDB(String id, String detail, String comp, int quan) {
		Connection conn = JDBCUtils.getConnection();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO stock VALUES ('"+id+"','"+detail+"','"+comp+"',"+quan+");");
			JOptionPane.showMessageDialog(null, "Product added to database");
			JDBCUtils.free(null, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void updateProductToDB(String id, String detail, String comp, int quan) {
		Connection conn = JDBCUtils.getConnection();
		try {
			Statement statement = conn.createStatement();
			int status = statement.executeUpdate("UPDATE stock set Detail = '"+detail+"', Company = '"+comp+"', Quantity = "+quan+" WHERE ProductID = '"+id+"';");
			if(status == 1){
		    	JOptionPane.showMessageDialog(null,  "Product updted");
			}else{
		    	JOptionPane.showMessageDialog(null,  "ProductID not found!");
			}
			JDBCUtils.free(null, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProductToDB(String id) {
		Connection conn = JDBCUtils.getConnection();
		try {
			Statement statement = conn.createStatement();
			int status = statement.executeUpdate("DELETE from stock WHERE ProductID = '"+id+"';");
		    if(status == 1){
		    	JOptionPane.showMessageDialog(null,  "Product deleted");
		    } else {
		    	JOptionPane.showMessageDialog(null,  "ProductID not found!");
		    }
		    JDBCUtils.free(null, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void searchProduct(String id) {
		Connection conn = JDBCUtils.getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
			if (!rs.next()) {
				JOptionPane.showMessageDialog(null,"No product found with this id!");
			}else{
				JOptionPane.showMessageDialog(null, "ProductID: "+id+"\nQuantity: "+rs.getString("Quantity"));
			}
			JDBCUtils.free(rs, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void searchCashier(String email) {
		Connection conn = JDBCUtils.getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from users WHERE Email = '"+email+"';");
			if (!rs.next()){
				JOptionPane.showMessageDialog(null,"No cashier found with this email!");
			}else{
				JOptionPane.showMessageDialog(null, "Email: "+email+"\nPassword: "+rs.getString("Password"));
			}    
			JDBCUtils.free(rs, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public boolean varifyLogin(String email, String pass) {
		boolean login = false;
		Connection conn = JDBCUtils.getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from users WHERE Email = '"+email+"' and Password = '"+pass+"';");
			if (!rs.next()){ 
				login = false;
			} else {
				login = true;
			}    
			JDBCUtils.free(rs, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return login;
	}

	@Override
	public void addCashier(String user, String pass) {
		Connection conn = JDBCUtils.getConnection();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO users VALUES ('"+user+"','"+pass+"');");
			JOptionPane.showMessageDialog(null, "Cashier added to database");
			JDBCUtils.free(null, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCashier(String user, String pass) {
		Connection conn = JDBCUtils.getConnection();
		try {
			Statement statement = conn.createStatement();
			int status=statement.executeUpdate("DELETE from users WHERE Email = '"+user+"' AND Password = '"+pass+"';");
			 if(status==1)
			    	JOptionPane.showMessageDialog(null,  "Cashier deleted");
			    else
			    	JOptionPane.showMessageDialog(null,  "Cashier not found!");
			 JDBCUtils.free(null, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public String searchPDetail(String id, int q) {
		Connection conn = JDBCUtils.getConnection();
		String rt = "";
		try {
			int quan;
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
			if (!rs.next()) {
				rt = "nill";
			} else {
				quan = Integer.parseInt(rs.getString("Quantity")) - q;
				if(quan<0){
					rt = "item is out of stock";
				} else {
					rt = rs.getString("Detail")+"%"+rs.getString("Company");
					statement.executeUpdate("UPDATE stock set Quantity = "+quan+" WHERE ProductID = '"+id+"';");
				}	
			}    
			JDBCUtils.free(rs, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return rt;
	}

	@Override
	public void addSaleToDB(Object[] data, ArrayList<String> comp, String name) {
		Connection conn = JDBCUtils.getConnection();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String d = dateFormat.format(date); 
		try {
			Statement statement = conn.createStatement();
			for(int x=0;x<data.length;x=x+5) {
				statement.executeUpdate("INSERT INTO sale VALUES ('"+data[x]+"','"+comp.get(0)+"','"+d+"','"+data[x+3]+"',"+data[x+4]+",'"+name+"');");
				comp.remove(0);
			}
			JDBCUtils.free(null, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> getSale(String date, String comp) {
		String q;
		ArrayList<String> r = new ArrayList<String>();
		if(comp.equals("All")){
			q = "Select * from sale WHERE Date = '"+date+"';";
		} else {
			q = "Select * from sale WHERE Date = '"+date+"' AND Company = '"+comp+"';";
		}
		
		Connection conn = JDBCUtils.getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(q);
			while(rs.next()){
				r.add(rs.getString("Date"));
				r.add(rs.getString("ProductID"));
				r.add(rs.getString("Company"));
				r.add(rs.getString("Payment"));
			}
			JDBCUtils.free(rs, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ArrayList<String> showStock(String comp) {
		String q;
		ArrayList<String> r = new ArrayList<String>();
		if(comp.equals("All")){	
			q = "Select * from stock;";
		} else {
			q = "Select * from stock WHERE Company = '"+comp+"';";
		}
		Connection conn = JDBCUtils.getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(q);
			while(rs.next()) {
				r.add(rs.getString("ProductID"));
				r.add(rs.getString("Detail"));
				r.add(rs.getString("Company"));
				r.add(rs.getString("Quantity"));
			}
			JDBCUtils.free(rs, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public String getPDetail(String id, int q) {
		Connection conn = JDBCUtils.getConnection();
		String rt = "";
		try {
			int quan;
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
			if (!rs.next()) {
				rt = "nill";
			} else {
				quan = Integer.parseInt(rs.getString("Quantity"))-q;
				if(quan < 0){
					rt = "item is out of stock";
				} else {
					rt = rs.getString("Detail")+"%"+rs.getString("Company");
					statement.executeUpdate("UPDATE stock set Quantity = "+quan+" WHERE ProductID = '"+id+"';");
				}
			}  
			JDBCUtils.free(rs, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return rt;
	}

	@Override
	public ArrayList<String> searchP(String id) {
		Connection conn = JDBCUtils.getConnection();
		ArrayList<String> data = new ArrayList<String>();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
			if (rs.next()) {
				data.add(rs.getString("Detail"));
				data.add(rs.getString("Company"));
				data.add(rs.getString("Quantity"));
			}
			JDBCUtils.free(rs, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void updateProduct(String id, int quan) {
		Connection conn = JDBCUtils.getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
			int q = 0;
			if(rs.next()) {
				q = Integer.parseInt(rs.getString("Quantity"))+quan;
				statement.executeUpdate("UPDATE stock set Quantity = "+q+" WHERE ProductID = '"+id+"';");
			}
			JDBCUtils.free(rs, statement, conn);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
}
