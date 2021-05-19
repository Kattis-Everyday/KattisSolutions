import java.util.*;

public class FlowShop{
	public static void main(String[] args){
		Scanner sc = new Scanner (System.in);
		int N = sc.nextInt();//(1≤𝑁,𝑀≤1000),
		int M = sc.nextInt();
		int [][] Pij= new int[N+10][M+10];
		for (int i=0; i< N; i++)
		{
			for (int j=0; j<M; j++)
			{
				Pij[i][j]=sc.nextInt();
			}
		}
		for (int j=0; j<M; j++)
		{
			for (int i=0; i<N; i++)
			{
				int startTime;
				if (i==0){
					if (j==0){
					startTime=0;
				    }
				    else{
				    startTime=Pij[i][j-1];
				    }
				}
				else{
					if (j>0){

					    startTime=Pij[i][j-1]>Pij[i-1][j]?Pij[i][j-1]:Pij[i-1][j];

				    }
					else{
						startTime=Pij[i-1][j];
					}
				}
				Pij[i][j]+=startTime;
			}
		}
		
		for (int i=0; i<N; i++){
			System.out.println(Pij[i][M-1]);
			System.out.println(' ');
		}



	}
}