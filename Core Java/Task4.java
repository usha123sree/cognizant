package cognizant;
import java.util.*;
public class Task4 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the year whether it is leap year or not");
		int num=sc.nextInt();
		if((num%4==0||num%100!=0)&&(num%400==0))
		{
			System.out.println("Leap Year");
		}
		else
		{
			System.out.println("Not a Leap Year");
		}
	}

}
