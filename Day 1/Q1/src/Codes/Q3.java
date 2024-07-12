package Codes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//3) Object Persistence inside database
//
//create a table "Product" with
//	pid number
//	qty number
//	cost decimal
//	pname varchar
//[ do not add any record ]
//
//define a class "Product" with
//	private int pid,qty;
//	private double cost;
//	private String pname;
//		setters,getters,parameterized constructor and toString()
//
//create 1 instance of above class , display it and then insert a row inside Product with the help of above instance.
//
//	now assign "null" to the above instance. That means your heap based object will be soon garbage collected , but you need not worry as you have saved the state of an object inside the database.
//
//	now fire a query to read that record and with the help of it initialize new object of "Product" and display it.


class Product{
	private int pid,qty;
	private double cost;
	private String pname;
	
	Product()
	{
		
	}
	
	Product(int pid,int qty,double cost,String pname)
	{
		this.pid=pid;
		this.qty=qty;
		this.cost=cost;
		this.pname=pname;
	}

	public int getPid() {
		return pid;
	}

	

	public int getQty() {
		return qty;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getCost() {
		return cost;
	}

	

	public String getPname() {
		return pname;
	}

	

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", qty=" + qty + ", cost=" + cost + ", pname=" + pname + "]";
	}
	
	
	
	
}
public class Q3 {
	public static void main(String[] args) {
		Product p1=new Product(5,20,5000,"Venky");
		System.out.println(p1);

		String ss="jdbc:mysql://localhost:3306/java";
		try(Connection con=DriverManager.getConnection(ss, "root","Sohan@1034"))
		{
			PreparedStatement ps=con.prepareStatement("insert into product values(?,?,?,?)");
			ps.setInt(1, p1.getPid());
			ps.setInt(2, p1.getQty());
			ps.setDouble(3, p1.getCost());
			ps.setString(4, p1.getPname());
			ps.execute();
			p1=null;
			Product p2=new Product();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from product");
			while(rs.next())
			{
				int pid=rs.getInt(1);
				int Qty=rs.getInt(2);
				double Cost=rs.getDouble(3);
				String Pname=rs.getString(4);
				
				
				p2.setPid(pid);
				p2.setQty(Qty);
				p2.setCost(Cost);
				p2.setPname(Pname);
				
				System.out.println("new:"+p2);
//				System.out.println("Pid:"+pid+"\t"+"Qty:"+Qty+"\t"+"Cost:"+Cost+"\t"+"Pname:"+Pname);
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
				
		
	}
}
