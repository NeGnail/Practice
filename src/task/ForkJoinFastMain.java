package task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinFastMain extends RecursiveTask<List>{
	private final ConcurrentHashMap<Integer,Object> set;
	private final int[] array;
	private final int left;
	private final int sum;
	private final int k;
	private static final Object value=new Object();
	
	
	public ForkJoinFastMain(int[] array,int sum,int k){
		this.array=array;
		set=new ConcurrentHashMap(array.length);
		for(int i=0;i<array.length;i++){
			set.put(array[i],value);
		}
		left=0;
		this.sum=sum;
		this.k=k;
	}
		
	
	public ForkJoinFastMain(ConcurrentHashMap set, int[] array, int left, int sum, int k) {
		this.set = set;
		this.array = array;
		this.left = left;
		this.sum = sum;
		this.k = k;
	}




	@Override
	protected List compute() {
		List result=new ArrayList(k);
		if(k==2){
			for(int i=left;i<array.length;i++){
				int a=array[i];
				int b=sum-a;
				if(set.containsKey(b)){
					result.add(a);
					result.add(b);
					return result;
				}
			}
			return result;
		}else{
			ForkJoinFastMain[] forkJoinFastMains=new ForkJoinFastMain[array.length-2];
			for(int i=left,j=0;i<array.length-2;i++,j++){
				ForkJoinFastMain forkJoinFastMain=new ForkJoinFastMain(set,array,i+1,sum-array[i],k-1);
				forkJoinFastMain.fork();
				forkJoinFastMains[j]=forkJoinFastMain;
			}
			for(int j=0;j<forkJoinFastMains.length;j++){
				List resultSub=forkJoinFastMains[j].join();
				if(resultSub.size()!=0){
					result.add(array[left]);
					result.addAll(resultSub);
					return result;
				}
			}
			return result;
		}
	}
	

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int[] resource=new int[]{5,15,9,10,46,16,6,46,56,8,5,9};
		ForkJoinFastMain main=new ForkJoinFastMain(resource,38,5);
        ForkJoinPool pool=new ForkJoinPool();
        Future<List> resultFuture=pool.submit(main);
       
        List<Integer> result=resultFuture.get();
        for(int i:result){
        	System.out.print(i+" ");
        }
	}
	
}
