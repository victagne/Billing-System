import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public  class AdminPanel extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuItem itmAddProduct;
	JMenu mnProduct;	
	JMenu mnCashier ;	
	JMenu mnStock ;	
	JMenu mnExport;
	ArrayList<JPanel> panels=new ArrayList<JPanel>();
	int cPanel=0;
	private JMenu mnSearch;	
	private JMenu mnSale;
	

	
	/**
	 * Create the frame.
	 */
	public AdminPanel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\Working Directory\\fianl project with sql\\Bill\\logo.png"));
		setTitle("Admin Panel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 840, 619);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnProduct = new JMenu("Product");
		menuBar.add(mnProduct);	
		
		mnProduct.add(actAddProduct);	
		mnProduct.add(actUpdateProduct);
		mnProduct.add(actDeleteProduct);
						
		mnCashier = new JMenu("Cashier");
		menuBar.add(mnCashier);		
		mnCashier.add(actAddCashier);		
		mnCashier.add(actDeleteCashier);
				
		mnStock = new JMenu("Stock");
		menuBar.add(mnStock);				
		mnStock.add(actShowStock);
				
		mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);	
		mnSearch.add(actSearchProduct);	
		mnSearch.add(actSearchCashier);
		
		mnSale = new JMenu("Sale");
		menuBar.add(mnSale);	
		
		mnSale.add(actPrintSale);
				
		mnExport = new JMenu("Account");
		menuBar.add(mnExport);
		
		//JMenuItem logout = new JMenuItem("Logout");
		mnExport.add(actLogout);
		//logout.addActionListener(this);
		
				
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		//ArrayList of command history
		panels.add(new addProduct());
		panels.add(new updateProduct());
		panels.add(new deleteProduct());
		panels.add(new addCashier());
		panels.add(new deleteCashier());
		panels.add(new showStock());
		panels.add(new searchProduct());
		panels.add(new searchCashier());
		panels.add(new Sale());
		getContentPane().add(panels.get(0));
		
	}


public AbstractAction actAddProduct = new AbstractAction("Add Product")
{	
	@Override
	public void actionPerformed(ActionEvent e) {		
		remove(panels.get(cPanel));
		revalidate();
		repaint();
		getContentPane().add(panels.get(0));
		setVisible(true);
		cPanel=0;
		setTitle("Add Product");
	}
};

public AbstractAction actUpdateProduct = new AbstractAction("Update Product")
{
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		remove(panels.get(cPanel));
		revalidate();
		repaint();
		getContentPane().add(panels.get(1));
		setVisible(true);
		cPanel=1;
		setTitle("Update Product");
	}
};

public AbstractAction actDeleteProduct = new AbstractAction("Delete Product")
{	
	@Override
	public void actionPerformed(ActionEvent e) {		
		remove(panels.get(cPanel));
		revalidate();
		repaint();
		getContentPane().add(panels.get(2));
		setVisible(true);
		cPanel=2;
		setTitle("Delete Product");
	}
};

public AbstractAction actAddCashier = new AbstractAction("Add cashier")
{	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		remove(panels.get(cPanel));
		revalidate();
		repaint();
		getContentPane().add(panels.get(3));
		setVisible(true);
		cPanel=3;
		setTitle("Add cashier");
	}
};


public AbstractAction actDeleteCashier = new AbstractAction("Delete cashier")
{
	@Override
	public void actionPerformed(ActionEvent e) {
		
		remove(panels.get(cPanel));
		revalidate();
		repaint();
		getContentPane().add(panels.get(4));
		setVisible(true);
		cPanel=4;
		setTitle("Delete cashier");
	}
};


public AbstractAction actShowStock = new AbstractAction("Show Stock")
{
	@Override
	public void actionPerformed(ActionEvent e) {
		
		remove(panels.get(cPanel));
		revalidate();
		repaint();
		getContentPane().add(panels.get(5));
		setVisible(true);
		cPanel=5;
		setTitle("Show stock");
	}
};

public AbstractAction actSearchProduct = new AbstractAction("Search Product")
{	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		remove(panels.get(cPanel));
		revalidate();
		repaint();
		getContentPane().add(panels.get(6));
		setVisible(true);
		cPanel=6;
		setTitle("Search Product");
	}
};


public AbstractAction actSearchCashier = new AbstractAction("Search Cashier")
{
	@Override
	public void actionPerformed(ActionEvent e) {
		
		remove(panels.get(cPanel));
		revalidate();
		repaint();
		getContentPane().add(panels.get(7));
		setVisible(true);
		cPanel=7;
		setTitle("Search Cashier");
	}
};



public AbstractAction actPrintSale = new AbstractAction("Print Sale")
{
	@Override
	public void actionPerformed(ActionEvent e) {
		
		remove(panels.get(cPanel));
		revalidate();
		repaint();
		getContentPane().add(panels.get(8));
		setVisible(true);
		cPanel=8;
		setTitle("Print Sale");
	}
};


public AbstractAction actLogout = new AbstractAction("Logout")
{	
	@Override
	public void actionPerformed(ActionEvent e) {		
		dispose();
	}
};

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}


