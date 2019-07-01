import java.util.ArrayList;

public interface IDAO {
	public void addProductToDB(String id,String detail,String comp,int quan);
	public void updateProductToDB(String id,String detail,String comp,int quan);
	public void deleteProductToDB(String id);
	public void searchProduct(String id);
	public void searchCashier(String email);
	public boolean varifyLogin(String email,String pass);
	public void addCashier(String user,String pass);
	public void deleteCashier(String user,String pass);
	public String searchPDetail(String id,int q);
	public void addSaleToDB(Object data[],ArrayList<String> comp,String name);
	public ArrayList<String> getSale(String date,String comp);
	public ArrayList<String> showStock(String comp);
	public String getPDetail(String id,int q);
	public ArrayList<String> searchP(String id);
	public void updateProduct(String id,int quan);
}