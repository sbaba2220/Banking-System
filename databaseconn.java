import java.sql.*; 
class databaseconn {

 
    public static void main(String[] args) {
        try
        {
            String Query = "select * from register where uid='RAGHU-0000002'";
           Class.forName("com.mysql.jdbc.Driver");
           Connection con =DriverManager.getConnection("jdbc:mysql://localhost/students","root","2220");
           Statement st=con.createStatement();
           ResultSet rs=st.executeQuery(Query);
           rs.next();
           String sname=rs.getString(2);
           System.out.println(sname);
           con.close();
        }
        catch(Exception e)
        {
            
        }
    }
    
}

