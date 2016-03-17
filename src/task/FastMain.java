package task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * @author LiWei
 *
 */
public class FastMain {
	private final HashSet set;
	private final int[] array;
	
	public FastMain(int[] array){
		this.array=array;
		set=new HashSet(array.length);
		for(int i=0;i<array.length;i++){
			set.add(array[i]);
		}
	}
		
	/**
	 * 时间复杂度为C(n,k-1)
	 * 空间复杂度为O(n)
	 * @param left
	 * @param sum
	 * @param k
	 * @return
	 */
	private List getResultOfK(int left,int sum,int k){
		List result=new ArrayList(k);
		if(k==2){
			for(int i=left;i<array.length;i++){
				int a=array[i];
				int b=sum-a;
				if(set.contains(b)){
					result.add(a);
					result.add(b);
					return result;
				}
			}
			return result;
		}
		for(int i=left;i<array.length;i++){
			int a=array[i];
			int b=sum-a;
			int subLeft=i+1;
			int subK=k-1;
			List resultSub=getResultOfK(subLeft,b,subK);
			if(resultSub.size()!=0){
				result.add(a);
				result.addAll(resultSub);	
				return result;
			}
		}
		return result;
	}
	
	public List getResultOfK(int sum,int k){
		return getResultOfK(0,sum,k);
	}
	
	public static void main(String[] args) {
		int[] resource=new int[]{5,15,9,10,46,16,6,46,56,8,5,9};
		FastMain main=new FastMain(resource);
		List<Integer> result=main.getResultOfK(38,5);
		if(result.size()!=0){
			for(int i:result){
				System.out.print(i+" ");
			}
		}
	}
}
