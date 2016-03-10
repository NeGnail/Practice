package task;


import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FastMain {
	private final int[] array;
	private final int length;
	
	public FastMain(int[] array){
		this.array=array;
		length=array.length;
	}
	
	private HashSet getHashSet(){
		HashSet set=new HashSet(length);
		for(int i=0;i<length;i++){
			set.add(array[i]);
		}
		return set;
	}
	
	public List getResultOfTwo(int sum){
		List result=new LinkedList();
		
		int length=array.length;
		HashSet set=getHashSet();
		
		Iterator<Integer> iterator=set.iterator();
		while(iterator.hasNext()){
			int a=iterator.next();
			int b=sum-a;
			if(set.contains(b)){
				iterator.remove();
				
				int[] resultUnit=new int[2];
				resultUnit[0]=a;
				resultUnit[1]=b;
				result.add(resultUnit);
			}
			
		}
		return result;
	}
	

	
	public static void main(String[] args) {
		FastMain main=new FastMain(new int[]{12,45,3,8,85,23,32,44,23,7,6,22,5,-5,0,-9,-1,-7,-5,5,23,56});
		List<int[]> result=main.getResultOfTwo(30);
		for(int[] resultUnit : result){
			System.out.println(resultUnit[0]+","+resultUnit[1]);	
		}
		
		
	}
}
