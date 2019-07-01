import java.util.ArrayList;

public class DBService {
	
	private ImplDAO implDAO;
	
	public DBService(){
		this.implDAO = new ImplDAO();
	}
	
	public void addProductToDB(String id,String detail,String comp,int quan){
		this.implDAO.addProductToDB(id, detail, comp, quan);
	}
	
	public void updateProductToDB(String id,String detail,String comp,int quan){
		this.implDAO.updateProductToDB(id, detail, comp, quan);
	}
	
	public void deleteProductToDB(String id){
		this.implDAO.deleteProductToDB(id);
	}
	
	public void searchProduct(String id){
		this.implDAO.searchProduct(id);
	}
	
	public void searchCashier(String email){
		this.implDAO.searchCashier(email);
	}
	
	public boolean varifyLogin(String email,String pass){
		return this.implDAO.varifyLogin(email, pass);
	}
	
	public void addCashier(String user,String pass){
		this.implDAO.addCashier(user, pass);
	}
	
	public void deleteCashier(String user,String pass){
		this.implDAO.deleteCashier(user, pass);
	}
	
	public String searchPDetail(String id,int q){
		return this.implDAO.searchPDetail(id, q);
	}
	
	public void addSaleToDB(Object data[],ArrayList<String> comp,String name){
		this.implDAO.addSaleToDB(data, comp, name);
	}
	
	public ArrayList<String> getSale(String date,String comp){
		return this.implDAO.getSale(date, comp);
	}
	
	public ArrayList<String> showStock(String comp){
		return this.implDAO.showStock(comp);
	}
	
	public String getPDetail(String id,int q){
		return this.implDAO.getPDetail(id, q);
	}
	
	public ArrayList<String> searchP(String id){
		return this.implDAO.searchP(id);
	}
	
	public void updateProduct(String id,int quan){
		this.implDAO.updateProduct(id, quan);
	}
}