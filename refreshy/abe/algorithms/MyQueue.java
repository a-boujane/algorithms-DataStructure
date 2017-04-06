package refreshy.abe.algorithms;

import java.util.Stack;
//Implementation of a Queue using Stacks
public class MyQueue<T> {
	int length=0;
	private Stack<T> addStack=new Stack<T>();
	private Stack<T> removeStack=new Stack<T>();

	void addElement(T element){
		if(!removeStack.isEmpty())
			for(int j=0;j<length;j++)
				addStack.push(removeStack.pop());
		length++;
		addStack.push(element);
	}
	
	T removeElement(){
		if(!addStack.isEmpty())
			for(int j=0;j<length;j++)
				removeStack.push(addStack.pop());
		length--;
		return removeStack.pop();
	}
	
	boolean isEmpty(){
		return addStack.isEmpty() && removeStack.isEmpty();
	}
	
}
