/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabankaccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Sai Baba
 */
public class JavaBankAccount {

    /**
     * @param args the command line arguments
     */
    Scanner sc=new Scanner(System.in);
    Scanner in=new Scanner(System.in);
		String firstname,lastname,address;
		int depmny;
	void createAccount()
	{
		System.out.println("Enter First name\n");
		firstname=sc.next();
		System.out.println("Enter Last name\n");
		lastname=sc.next();
		System.out.println("Enter Address\n");
		address=sc.next();
		System.out.println("Enter Deposit amount\n");
		depmny=sc.nextInt();
                try
        {
           String Query = "insert into account_master(first_name,last_name,address,balance) values('"+firstname+"','"+lastname+"','"+address+"','"+depmny+"')";
           Class.forName("com.mysql.jdbc.Driver");
           Connection con =DriverManager.getConnection("jdbc:mysql://localhost/bankaccount","root","2220");
           Statement st=con.createStatement();
           st.executeUpdate(Query);
           String sql=("select * from account_master order by acc_num desc limit 1");
           st=con.createStatement();
           ResultSet rs=st.executeQuery(sql);
           rs.next();
           String accnum=rs.getString(1);
           System.out.println("Thank You For Creating your account in our Bank. Your Account number is "+accnum);
           con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
	}
        void cashdeposit()
        {
            System.out.println("OK! Now enter your bank account number\n");
            int acc_num=sc.nextInt();
            System.out.println("Enter the deposit ammount");
            int dep_amnt=sc.nextInt();
            String deposit="Deposit";
            try
            {
                String query="update account_master set balance=balance+'"+dep_amnt+"' where acc_num='"+acc_num+"'";
                Connection con =DriverManager.getConnection("jdbc:mysql://localhost/bankaccount","root","2220");
                Statement st=con.createStatement();
                st.executeUpdate(query);
                String querybal="select balance from account_master where acc_num='"+acc_num+"'";
                ResultSet rs=st.executeQuery(querybal);
                rs.next();
                String balance=rs.getString(1);
                Calendar calendar = Calendar.getInstance();
                java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
                String trans_details="insert into transaction_details values('"+acc_num+"','"+dep_amnt+"','"+startDate+"','"+deposit+"')";
                st.executeUpdate(trans_details);
                con.close();
                System.out.println("Your amount is successfully deposited.\n");
                System.out.println("Your account balance is "+balance);
            }
            catch(Exception e)
            {
                System.out.println("Account number is not correct! Please enter valid account number.");
            }
        }
        void cashwithdrawl()
        {
            System.out.println("OK! Now enter your bank account number\n");
            int acc_num=sc.nextInt();
            System.out.println("Enter the withdrawl ammount");
            int dep_amnt=sc.nextInt();
            String deposit="Withdrawed";
            try
            {
                String query="update account_master set balance=balance-'"+dep_amnt+"' where acc_num='"+acc_num+"'";
                Connection con =DriverManager.getConnection("jdbc:mysql://localhost/bankaccount","root","2220");
                Statement st=con.createStatement();
                st.executeUpdate(query);
                String querybal="select balance from account_master where acc_num='"+acc_num+"'";
                ResultSet rs=st.executeQuery(querybal);
                rs.next();
                String balance=rs.getString(1);
                Calendar calendar = Calendar.getInstance();
                java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
                String trans_details="insert into transaction_details values('"+acc_num+"','"+dep_amnt+"','"+startDate+"','"+deposit+"')";
                st.executeUpdate(trans_details);
                con.close();
                System.out.println("Your amount is successfully withdrawed.\n");
                System.out.println("Your account balance is "+balance);
            }
            catch(Exception e)
            {
                System.out.println("Account number is not correct! Please enter valid account number.");
            }
        }
	void transactions()
	{
            try
            {
                System.out.println("Welcome to transactions sections! Please enter your account number");
                int acc_num=sc.nextInt();
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/bankaccount","root","2220");
                Statement st=con.createStatement();
                String query="select * from transaction_details where acc_num='"+acc_num+"'";
                ResultSet rs=st.executeQuery(query);
                ResultSetMetaData r = rs.getMetaData();
                int total_cols = r.getColumnCount();
                System.out.println("Your Full transactions are");
                while (rs.next()) 
                {
                    for(int i=1;i<=total_cols;i++)
                        System.out.print(rs.getString(i) + " ");
                    System.out.println();
                }
                String querybal="select balance from account_master where acc_num='"+acc_num+"'";
                ResultSet s=st.executeQuery(querybal);
                s.next();
                String balance=s.getString(1);
                System.out.println("Your account balance is "+balance);
            }   
            catch (Exception e)
            {
                System.out.println("Account number is not correct! Please enter valid account number.");
            }
	}
	
    public static void main(String[] args) {
        // TODO code application logic here
        int ch;
        Scanner in=new Scanner(System.in);
        JavaBankAccount ob=new JavaBankAccount();
	System.out.println("\t\t\t\tWelcome to ABC Bank \n\tEnter your choice\n 1 for new Account opening\n 2 for Cash Deposit\n"
                + " 3 for Cash Withdrawl\n 4 for Bank Statement\n 5 for Exit\n");
        do
        {
            ch=in.nextInt();
            switch(ch)
            {   
                case 1:
                {
                    ob.createAccount();
                    break;
                }
                case 2:
                {
                    ob.cashdeposit();
                    break;
                }
                case 3:
                {
                    ob.cashwithdrawl();
                    break;
                }
                case 4:
                {
                    ob.transactions();
                    break;
                }
                case 5:
                {
                    System.out.println("Thank you for visiting our Bank!");
                    System.exit(0);
                }
            }
            System.out.println("\tEnter your choice\n 1 for new Account opening\n 2 for Cash Deposit\n"
                + " 3 for Cash Withdrawl\n 4 for Bank Statement\n 5 for Exit\n");
        }while(ch<6&&ch>0);
    }
    
}
