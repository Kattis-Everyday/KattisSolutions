//  Rings
//  Writen by mcr on 2021-05-20


import java.util.*;
import java.io.*;
public class Rings{
	public static void main(String [] args){
		Scanner sc = new Scanner (System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int [][] maps = new int [n][m];
		char [][] mapC = new char [n][m];
		int [] adjLis ={1,0, -1,0,  0,1, 0,-1, 1,1, 1,-1, -1,1, -1,-1};
		Queue<Node> bfsQueue=new LinkedList<>();

		for (int i=0; i< n; i++){
			mapC[i] = sc.next().toCharArray();
			for (int j=0; j<m; j++){
				//char c = sc.next().charAt(0);
				char c = mapC[i][j];
				if (c=='.'){

					maps[i][j] = 0;
					bfsQueue.offer(new Node(i,j));

				}
				else{
				    maps[i][j] = -1;
				}
			}
		}



		for (int i=0; i<n;i ++){
			bfsQueue.offer(new Node(i,-1));
			bfsQueue.offer(new Node(i,m));

		}
		for (int j=0; j<m;j ++){
			bfsQueue.offer(new Node(-1,j));
			bfsQueue.offer(new Node(n,j));

		}

        //System.out.println(1);
		int layer=0;
		while (!bfsQueue.isEmpty()){
			Queue<Node> innerQueue =new LinkedList<>();
            while (!bfsQueue.isEmpty())
            {
			Node pos= bfsQueue.poll();
			int rowPos = pos.r;
			int colPos = pos.c;
			for (int i=0; i<4; i++)
			{
				int newRow=rowPos+adjLis[2*i];
				int newCol=colPos+adjLis[2*i+1];
				if (newRow>=0 && newRow<n && newCol>=0 && newCol<m && maps[newRow][newCol]==-1){


					maps[newRow][newCol]=layer+1;
				    innerQueue.offer(new Node(newRow,newCol));
				}
			}
            }
            layer++;
            //System.out.println(innerQueue.size());

            bfsQueue = innerQueue;
            //Queue<Node> innerQueue =new LinkedList<>();

		}


        int numPos=2;
		if (layer>=10) numPos=3;
		String p = new String (".");
		for (int i=0; i< n; i++){
			String strRow=new String("");
			for (int j=0; j<m; j++){
				
				if (maps[i][j] == 0){
					strRow += p.repeat(numPos);
				}
				else{
					String strNum =String.valueOf(maps[i][j]);
                    int numPoint = numPos - strNum.length();
					strRow += (p.repeat(numPoint) + strNum);

				}
			}
			System.out.println(strRow+'\n');

		}


	
}
static class Node {
        int r, c;

        public Node(int a, int b) {
            r = a;
            c = b;
        }
}
}



