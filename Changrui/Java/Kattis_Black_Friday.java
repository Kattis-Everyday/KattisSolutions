import java.util.*;
//Created by Mu 05-14

public class BlackFriday{
    public static void main(String[] args){
	Scanner sc= new Scanner(System.in);
	int n=sc.nextInt(); //1≤𝑛≤100
	int [] hash=new int[6];
	ArrayList<Integer> indices = new ArrayList<>();
	for (int i=0; i<6; i++)
	{
		hash[i]=0;
	}
	for (int i = 0; i<n; i++){
		int num=sc.nextInt();
		hash[num-1]++;
		indices.add(num);
	}
	for (int i = 5; i>=0; i--)
	{
		if (hash[i]==1){
			System.out.println(indices.indexOf(i + 1) + 1);
			return ;
		}
	}
	System.out.println("none");
}
	
}