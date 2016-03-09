package task;

public class Sort {
	public static void sort(int[] array){
		sort(array,0,array.length-1);
	}
	
	private static void sort(int[] array,int left,int right){
		if(left<right){
			int partitioner=partition(array, left, right);
			sort(array, left, partitioner-1);
			sort(array, partitioner+1, right);
		}
	}
	
	private static int partition(int[] array,int left,int right){
		int i=left;
		int j=right+1;
		int flagValue=array[left];
		while(true){
			while(array[++i]<flagValue){
				if(i==right){
					break;
				}
			}
			while(array[--j]>flagValue){
				if(j==left){
					break;
				}
			}
			if(i>=j){
				break;
			}
			swap(array,i,j);
		}
		swap(array, left, j);
		return j;
	}
	
	private static void swap(int[] array,int a,int b){
		int temp=array[a];
		array[a]=array[b];
		array[b]=temp;
	}
	
	public static void main(String[] args) {
		int[] array={12,45,3,8,85,23,32,44,66,56,63,95,35,65};
		Sort.sort(array);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}
}
