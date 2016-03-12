package task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * ①这两天一直在琢磨这个问题的解法，也尝试了很多方法，
 *   但是能力确实不足，还是想不出一个好的算法来降低K个
 *   数情况下的时间复杂度。只以O(n^(k-1))的时间复杂度
 *   解决了这个问题。
 * ②在面试的时间中，我最多可以想到利用HashSet使得两个数
 *   的情况下的时间复杂度降低到O(n)。不得不承认自己面试是失败的。
 * ③针对这个问题，我目前的想法是，如果只利用Hash表存储array中的每个数，那么时间复杂度会随着k的值而呈现次方的递增。
 *   也可以利用Hash表存储array中多个数的乘积，例如求k=4的情况，就可以使得Hash表存储array中两两相乘的积。
 *   然后通过像求两个数的情况一样去遍历一遍Hash表，这样可以使得时间复杂度降低到O(n^2),空间复杂度为O(n^2)。
 *   这种解法通过空间复杂度的增加而降低了时间复杂度。但是随着k的增大，时间复杂度还是会依照次方级增加。而且空间复杂度也会很大。
 *   所以感觉自己能力确实不足，还是不能有效地降低时间复杂度，希望您可以告诉正确的解法。
 * ④另外，自己在想算法的过程中，想到了以多线程的方式来解决这个问题。
 *   这个解法和算法几乎没有任何关系，就是自己一时想起，也提交上来了,在ForkJoinFastMain类中。
 *            O(∩_∩)O谢谢你的耐心，没想到您居然看得这么细，哈哈。
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
	 * 时间复杂度为O(n^(k-1))
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
