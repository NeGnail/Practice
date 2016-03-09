package task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;


/**
 * ①对于两个数的情况，我先进行排序，然后再用头尾两个引用进行查找。
 *   除了第一次需要排序外，其余每次查找时间复杂度为O(n)，空间复杂度为O(1)。
 * ②对于任意数的情况，我实在想不出好的解法来降低时间复杂度。
 * 	   先进行排序，然后以一个栈依次将数组中的数入栈，
 *   每次入栈一个数就判断栈中数的和是否大于了指定参数，
 *   如果小于，就继续入栈数组中后面一个数，
 *   如果大于，就弹出刚刚入栈的这个数，并且再将此时栈顶的数出栈，将刚刚出栈的这个数在数组中的下一个位置的数入栈
 *   如果等于，就将栈中的元素存储下来。
 * 
 *   
 * @author Lw
 *
 */
public class Main {
	private final int[] array;

	public Main(int[] array) {
		this.array = array;
		Sort.sort(array);
	}
	/**
	 * 两个数的情况
	 * 如果不存在则返回null
	 * @param sum
	 * @return
	 */
	public Set getResultOfTwo(int sum){
		int left=0;
		int right=array.length-1;	
		Set resultOfTwo=new HashSet();
		while(left<right){
			int sumTemp=array[left]+array[right];
			if(sumTemp<sum){
				left++;
			}else if(sumTemp>sum){
				right--;
			}else{
				ArrayList arrayList=new ArrayList(2);
				arrayList.add(array[left]);
				arrayList.add(array[right]);
				resultOfTwo.add(arrayList);			
				left++;
			}
		}
		return resultOfTwo;
	}
	
	/**
	 * 任意个数的情况
	 * 如果不存在则返回null
	 * @param sum
	 * @return
	 */
	public Set getResultOfAll(int sum){
		Set result=new HashSet();
		MyStack stack=new MyStack();
		stack.push(array[0],0);
		int i=1;
			while(i<array.length){
				stack.push(array[i],i);
				int stackSum=stack.getSum();
				if(stackSum>sum){
					if(stack.getLength()<2){
						break;
					}
					stack.pop();
					i=stack.pop();
					i++;
				}else if(stackSum==sum){
					result.add(stack.getAllValues());
				}else{
					i++;
				}
			}
		return result;
	}
	
	private void add(LinkedList<Integer> result, Integer[] resultOfTwo) {
		for(int i=0;i<resultOfTwo.length;i++){
			result.add(resultOfTwo[i]);
		}
		
	}
	public static void main(String[] args) {
		Main main=new Main(new int[]{12,45,3,8,85,23,32,44,23,7,6,22,5,-5,0,-9,-1,-7,-5,5,23,56});
		Set<List> result=main.getResultOfTwo(30);
		for(List l:result){
			System.out.print(l.toString());	
		}
		
		System.out.println();
		System.out.println("**********");
		
		Set<List> resultOfAll=main.getResultOfAll(30);
		for(List l:resultOfAll){
			System.out.print(l.toString());	
		}
	}
	
}
