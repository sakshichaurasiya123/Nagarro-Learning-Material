import java.sql.*;

public class DemoClass {
	public static void main(String arg[]) throws Exception {
		String url="jdbc:mysql://localhost:3306/emp";
		String username="root";
		String password="root";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,username,password);
		//PreparedStatement stmt=con.prepareStatement("insert into employee values('Muskan',123456789)");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from employee");
		while(rs.next())
			System.out.println(rs.getString(1)+" "+rs.getInt(2));
		/*int i=0;
		i=stmt.executeUpdate();
		if(i>0)
		{
			System.out.println("Row Inserted");
		}
		*/
		
		con.close();
		
		
	}
}


/*
import java.sql.*;

public class DemoClass {
   static final String DB_URL = "jdbc:mysql://localhost/emp";
   static final String USER = "root";
   static final String PASS = "root";
   static final String QUERY = "SELECT * FROM employee";

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);) {
         // Extract data from result set
         while (rs.next()) {
            // Retrieve by column name
            System.out.print("ID: " + rs.getInt("name"));
            System.out.print(", Age: " + rs.getInt("password"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}

*/