package cognizant;
import java.sql.*;
public class Task31 {
	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://localhost:3306/mydata";
			Connection con = DriverManager.getConnection(url,"root","usha");
			String q="select * from mytab1";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(q);
			while(rs.next())
			{
				System.out.println("id: "+rs.getInt(1) +" "+"name: "+rs.getString(2));
			}
		}catch(SQLException e){
			e.printStackTrace();
   				
		}


	}

}
		