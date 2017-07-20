import java.util.Scanner;
class bankAcc
{
	Scanner sc=new Scanner(System.in);
		String firstname,lastname,address;
		int accnum,depmny;
	void createAccount()
	{
		System.out.println("Enter Acc Number\n");
		accnum=sc.nextInt();
		System.out.println("Enter First name\n");
		firstname=sc.next();
		System.out.println("Enter Last name\n");
		lastname=sc.next();
		System.out.println("Enter Address\n");
		address=sc.next();
		System.out.println("Enter Deposit amount\n");
		depmny=sc.nextInt();
	}
	/*void display()
	{
		System.out.println(accnum+"\n"+firstname+"\n"+lastname+"\n"+address+"\n"+depmny);
	}*/
	
		
	public static void main(String args[])
	{
		bankAcc ob=new bankAcc();
		ob.createAccount();
		ob.display();		
	}
}
