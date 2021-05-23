import java.util.*;




public class AlmostUnionFind{


	public static void main(String [] args){

		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
		int n = sc.nextInt();
		int m = sc.nextInt();
        int [] hashLis = new int[n+10];
		UnionFind UF = new UnionFind(n);
		for (int j=0; j<m; j++){
			int order = sc.nextInt();
			if (order == 1){
				int p = sc.nextInt();
				int q = sc.nextInt();
				UF.unionSet(p,q);
			}
			else if (order == 2){
				int p = sc.nextInt();
				int q = sc.nextInt();
				UF.movEle(p,q);
				
			}
			

			
			else if (order ==3){
				int p = sc.nextInt();
				System.out.println(UF.sizeOfSet(p)+" "+UF.sumOfSet(p));
			}
		}

	}
}


	public class UnionFind{
		private ArrayList<Integer> p, rank, setSize, setSum;
		private List<LinkedList<Integer>> invertP;
		private int numSets;

		public UnionFind (int N){ //1-based
			p = new ArrayList<>(N);
			rank = new ArrayList <> (N);
			setSize = new ArrayList <> (N);
			invertP = new LinkedList<LinkedList<Integer>>();
			numSets = N;
			for (int i=1; i<=N ; i++){
				p.add(i);
				setSum.add(i);
				rank.add(0);
				setSize.add(1);
				invertP.add(new LinkedList<Integer>());
			}
		}

		public int findSet(int i){
			if (p.get(i) == i) return i;
			else {
				int ret = findSet (p.get(i)); p.set(i,ret);
				return ret;
			}
		}

        public int getSize (int i) {return setSize.get(i);}
        public int getSum (int i) {return setSum.get(i);}
		public Boolean isSameSet(int i, int j){return findSet(i) == findSet(j);}

		public void unionSet(int i, int j){
			if (! isSameSet(i,j)){
				numSets--;
				int x = findSet(i), y=findSet(j);
				//rank is used to keeep the tree sort
				if (rank.get(x)>rank.get(y)){
					p.set(y,x); 
					setSize.set(x,setSize.get(x)+setSize.get(y));
					setSum.set(x,setSum.get(x)+setSum.get(y));
					invertP.get(x).add(y);

				}
				else{
					p.set(x,y);
					seSize.set(y, setSize.get(y)+setSize.get(x));
					setSum.set(y,setSum.get(x)+setSum.get(y));
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
			int successor = retrieve.remove();
			x = findSet(i);
			y = findSet(j);
			if (x!=i) p.set(successor,x);
			else 
				{
					x = successor;
					p.set(successor,x);

				}
			for (int i=0; i<retrieve.size(); i++){
				p.set(retrieve.get(i),successor);
			}
			invertP.addAll(successor, retrieve);
			p.set(retrieve.get(i),y);
			seSize.set(x, setSize.get(x)-1);
			seSize.set(y, setSize.get(y)+1);
			setSum.set(x,setSum.get(x)-i);
			setSum.set(y,setSum.get(y)+i);


		}


		public int numDisjointSets() {return numSets;}
		public int sizeOfSet(int i){return setSize.get(findSet(i));}
		public int sumOfSet(int i){return setSum.get(findSet(i));}
		
}
}


