package task;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyStack {
	private final LinkedList<Element> elements;
	private int sum;

	public MyStack() {
		this.elements = new LinkedList();
	}
	public void push(int value,int index){
		elements.add(new Element(value,index));
		sum+=value;
	
	}
	public int pop(){
		Element element=elements.removeLast();
		sum-=element.value;
		return element.index;
	}
	
	public int getSum() {
		return sum;
	}
	
	public List getAllValues(){
		List list=new LinkedList();
		for(Element element:elements){
			list.add(element.value);
		}
		return list;
	}
	
	public int getLength(){
		return elements.size();
	}
	
	private class Element{
		private int value;
		private int index;
		public Element(int value, int index) {
			super();
			this.value = value;
			this.index = index;
		}
		
	}
}
