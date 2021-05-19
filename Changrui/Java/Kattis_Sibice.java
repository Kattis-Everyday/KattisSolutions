import java.util.*;


public class Sibice{
	public static void main(String[] args){
	Scanner scanner = new Scanner(System.in);
	int  N,W,H;
	N = scanner.nextInt();
	W = scanner.nextInt();
	H = scanner.nextInt();
	double judge= Math.pow(W,2) + Math.pow(H,2);
	for (int i=0; i<N; i++){
		int s=scanner.nextInt();
		
		double d= Math.pow(s,2);
		//System.out.print(d);
		if (d>judge){
			System.out.print("NE\n");
		}
		else{
			System.out.print("DA\n");
		}

	}
}

}