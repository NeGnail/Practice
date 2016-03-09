package task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
