package project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class App {
private static final String path="oracle.jdbc.driver.OracleDriver";
private static final String url="jdbc:oracle:thin:@localhost:1521:xe";
private static final String un="system";
private static final String pw="system";
private static  Connection con=null;
private static  Statement stmt=null;
private static  PreparedStatement pstmt=null;
private static  ResultSet res=null;
private static Scanner scan=new Scanner(System.in);
	public static void main(String[] args) {
		try {
			App p=new App();
			p.loading();
			p.conn();
			while(true){
				System.out.println("Press 1 to Create Table");
				System.out.println("Press 2 to Insert Values");
				System.out.println("Press 3 to Update Values");
				System.out.println("Press 4 to Delete values");
				System.out.println("Press 5 to Display The Table");
				System.out.println("Press 6 to Display Specific Row Of Table");
				System.out.println("Press 7 to STOP");
				System.out.println("Enter your choice");
				Scanner in=new Scanner(System.in);
				int ch=in.nextInt();
				switch(ch) {
				case 1:
					   p.create();
						break;
				case 2:
					   p.insert();
						break;
				case 3:
					   p.update();
						break;
				case 4:
					   p.delete();
						break;
				case 5:
					   p.display();
						break;
				case 6:
					   p.displayspecific();
						break;
				default:{
					   System.out.println("Project Stop");
					   System.exit(0);
					}
					}
				}
			}
				catch (Exception e) {
				 e.printStackTrace();
				}
				finally {
					try {
						if (stmt!=null) {
							stmt.close();
						}
						if (pstmt!=null) {
							pstmt.close();
						}
						if (con!=null) {
							con.close();
						}
						if (res!=null) {
							res.close();
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		public void loading()throws ClassNotFoundException {
			Class.forName(path);
			System.out.println("Driver is loaded sucessful");
			}	
		
		public void conn() throws SQLException{
			con=DriverManager.getConnection(url,un,pw);
			System.out.println("connection established successfully");
			}
		public void create() throws SQLException{
			String Querry="create table project(id int,name varchar2(20),age int)";
			stmt=con.createStatement();
			stmt.executeUpdate(Querry);
			System.out.println("Table created successfully");
		}
		public void insert() throws SQLException{
			String query="insert into project values(?,?,?)";
			System.out.println("enter the id");
			int id=scan.nextInt();
			System.out.println("enter name");
			String name=scan.next();
			System.out.println("enter age");
			int age=scan.nextInt();
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.executeUpdate();
			System.out.println("inserted successfully");
		}
		public void update() throws SQLException {
			String query="update project set name=? where id=?";
			System.out.println("enter name to update");
			String name=scan.next();
			System.out.println("enter id");
			int id=scan.nextInt();
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			System.out.println("Query updated successfully");
		}
		public void delete() throws SQLException {
			String Q3="delete from project where id=?";
			System.out.println("enter id to delete row");
			int id=scan.nextInt();
			pstmt=con.prepareStatement(Q3);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("delete successfully");
		}
		public void display() throws SQLException{
			String query="select * from project";
		    stmt=con.createStatement();
		    res=stmt.executeQuery(query);
		    while(res.next()==true) {
		    	int id=res.getInt("id");
		    	String name=res.getString("name");
		    	int age=res.getInt("age");
		    	System.out.println(id+" "+name+" "+age);
		}
		    System.out.println("display successful");
		}
		public void displayspecific() throws SQLException {
			String query="select * from project where name=?";
			System.out.println("enter name to display its data");
			String name=scan.next();
		    pstmt=con.prepareStatement(query);
		    pstmt.setString(1,name);
		    res=pstmt.executeQuery();
		    while(res.next()==true) {
		    	int id=res.getInt("id");
		    	String name1=res.getString("name");
		    	int age=res.getInt("age");
		    	System.out.println(id+" "+name1+" "+age);
		}
		    System.out.println("Row updated successfully");
		}
		}
