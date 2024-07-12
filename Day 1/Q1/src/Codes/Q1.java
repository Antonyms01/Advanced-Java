package Codes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//1) create a table "Student"
//
//rollno,name,age
//
//write a java program to accept rollno,name,age from user and insert a row.
//
//after inserting that row try to view using a query.


class Q1{
	public static void main(String[] args) {
	  String take="jdbc:mysql://localhost:3306/java";
	  try(Connection con=DriverManager.getConnection(take,"root","Sohan@1034"))
	  {
		  PreparedStatement pst=con.prepareStatement("insert into student values(?,?,?)");
		  pst.setInt(1, 2);
		  pst.setString(2, "Saurabh");
		  pst.setInt(3, 22);
		  pst.execute();
		  Statement st=con.createStatement();
		  ResultSet rs=st.executeQuery("select * from student");
		  while(rs.next())
		  {
			  int roll=rs.getInt("roll");
			  String name=rs.getString("name");
			  int age=rs.getInt("age");
			  System.out.println("Roll No:"+roll+"Name:"+name+"Age:"+age);
		  }
	  }
	  catch(Exception e)
	  {
		  System.out.println(e);
	  }
	  
	}
}