/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabankaccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Sai Baba
 */
public class databaseconn {
    
 
    public static void main(String[] args) {
        try
        {
            String Query = "select * from register where uid='RAGHU-0000002'";
           Class.forName("com.mysql.jdbc.Driver");
           Connection con =DriverManager.getConnection("jdbc:mysql://localhost/0bankaccount","root","2220");
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
