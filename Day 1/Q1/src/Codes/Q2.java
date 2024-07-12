package Codes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//
//2) create table "Employee"
//empid,empname,desig
//
//add 5 records manually.
//
//Now write a java program to accept "designation" from user and retrieve employees with the
//given designation.
public class Q2 {
	public static void main(String[] args) {
		String take="jdbc:mysql://localhost:3306/java";
		try(Connection con=DriverManager.getConnection(take, "root","Sohan@1034"))
		{
			System.out.println("Enter Emp Designation:");
			Scanner sc=new Scanner(System.in);
			String desig=sc.next();
			PreparedStatement ps=con.prepareStatement("insert into emp values(?,?,?)");
			ps.setInt(1, 1);
			ps.setString(2, "Sohan");
			ps.setString(3, desig);
			ps.execute();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from emp");
			while(rs.next())
			{
				int empid=rs.getInt(1);
				String empname=rs.getString(2);
				String empdesig=rs.getString(3);
				System.out.println("Emp Id:"+empid+"Emp Name:"+"\t"+empname+"\t"+"Designation:"+empdesig);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
