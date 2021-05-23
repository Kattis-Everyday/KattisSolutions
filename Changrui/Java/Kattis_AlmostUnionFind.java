import java.util.*;

class AlmostUnionFind{


	public static void main(String [] args){

		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
		int n = sc.nextInt();
		int m = sc.nextInt();
        //int [] hashLis = new int[n+10];
		UnionFind UF = new UnionFind(n);
		for (int j=0; j<m; j++){
			int order = sc.nextInt();
			if (order == 1){
				int p = sc.nextInt();
				int q = sc.nextInt();
				UF.unionSet(p,q);
				//UF.allSum();
			}
			else if (order == 2){
				int p = sc.nextInt();
				int q = sc.nextInt();
				UF.movEle(p,q);
				//UF.allSum();
				
			}
			

			
			else if (order ==3){
				int p = sc.nextInt();
				System.out.println(UF.sizeOfSet(p)+" "+UF.sumOfSet(p));
				//UF.allSum();
				//System.out.println(UF.findSet(p)+"\n");
			}
			//UF.travalSet();
		}

	}
}


	
}
class UnionFind{
		private ArrayList<Integer> p, rank, setSize, setSum;
		private List<LinkedList<Integer>> invertP;
		private int numSets;

		public UnionFind (int N){ //1-based
			p = new ArrayList<>(N+1);
			rank = new ArrayList <> (N+1);
			setSize = new ArrayList <> (N+1);
			setSum = new ArrayList <> (N+1);
			invertP = new LinkedList<LinkedList<Integer>>();
			numSets = N;
			for (int i=0; i<=N ; i++){
				p.add(i);
				setSum.add(i);
				rank.add(0);
				setSize.add(1);
				invertP.add(new LinkedList<Integer>());
				//invertP.get(i).add(i);

			}
			//System.out.println("set Done!\n");
		}

		public int findSet(int i){
			//System.out.println(i+".  --------\n");
			if (p.get(i) == i) return i;
			else {
				int ret = findSet (p.get(i)); p.set(i,ret);
				p.set(i,ret);
				invertP.get(ret).add(i);
				return ret;
			}
		}

		public void travalSet(){
			for (int i=1; i<p.size(); i++){
				System.out.println(p.get(i)+" ");
				
			}
			System.out.println("\n");
		}

       
		public Boolean isSameSet(int i, int j){
			//System.out.println("sSameSet:  i: "+i+" j: "+j);
			return findSet(i) == findSet(j);
		}

		public void unionSet(int i, int j){
			if (! isSameSet(i,j)){

				numSets--;
				//System.out.println("i: "+i+" j: "+j);
				int x = findSet(i), y=findSet(j);
				numSets--;
				//System.out.println("x: "+x+" y: "+y);
				//rank is used to keeep the tree sort
				if (rank.get(x)>rank.get(y)){
					p.set(y,x); 
					setSize.set(x,setSize.get(x)+setSize.get(y));
					setSum.set(x,setSum.get(x)+setSum.get(y));

					setSize.set(y,0);
					setSum.set(y,0);
					invertP.get(x).add(y);

				}
				else{
					p.set(x,y);
					setSize.set(y, setSize.get(y)+setSize.get(x));
					setSum.set(y,setSum.get(x)+setSum.get(y));
					setSize.set(x,0);
					setSum.set(x,0);
					invertP.get(y).add(x);
					if (rank.get(x) == rank.get(y)) 
						{
							rank.set(y, rank.get(y)+1); 
						}
				}
			}
		}

		public void movEle(int i, int j){//move element i to the set containing j
			LinkedList<Integer> retrieve = invertP.get(i);
			//int successor = retrieve.poll();
			int x = findSet(i);
			int y = findSet(j);
			if (x!=i) {
				//p.set(successor,x);
				for (int t = 0; t< retrieve.size(); t++){
					System.out.println(retrieve.get(t)+"\n");
					p.set(retrieve.get(t),x);
					invertP.get(x).add(retrieve.get(t));
				}

			}
			else 
				{
					if (retrieve.size()!=0){
						int successor = retrieve.poll();
					for (int t=0; t<retrieve.size(); t++){
				p.set(retrieve.get(t),successor);
				invertP.get(successor).add(retrieve.get(t));
				setSum.set(successor,setSum.get(x));
				setSum.set(x,0);
				setSize.set(successor,setSize.get(x));
				setSize.set(x,0);
				x = successor;
                p.set(x,x);
			}
		}
			else{
				unionSet(i, j);
				return ;
			
			}
					
            
			}
			
			
			p.set(p.get(i),y);
			setSize.set(x, setSize.get(x)-1);
			setSize.set(y, setSize.get(y)+1);
			setSum.set(x,setSum.get(x)-i);
			setSum.set(y,setSum.get(y)+i);



		}

		public void allSum(){
			for (int i=0; i<setSum.size(); i++){
				
				System.out.println("Size"+i+setSize.get(i)+"\n");
			}
		}


		public int numDisjointSets() {return numSets;}
		public int sizeOfSet(int i){return setSize.get(findSet(i));}
		public int sumOfSet(int i){return setSum.get(findSet(i));}
		
}




