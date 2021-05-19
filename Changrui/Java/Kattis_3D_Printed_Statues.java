import java.util.*;

public class EDprintedstatues{
	public static void main(String[] args){
		//Kattio io = new Kattio(System.in, System.out);
		Scanner jin = new Scanner (System.in);
		int statues = jin.nextInt();
		System.out.println((int)Math.ceil(Math.log(statues)/Math.log(2))+1);
	}
}