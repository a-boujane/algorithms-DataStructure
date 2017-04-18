package refreshy.abe.algorithms;

public class FastQueue<T> {

	int size;
	LinkedListNode<T> fo =null;
	LinkedListNode<T> li =null;
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public LinkedListNode<T> add(T data){
		LinkedListNode<T> newly = new LinkedListNode<T>(data);
		size++;
		if(size==1){
			li=newly;
			fo=newly;
		}
		else{
			this.li.next=newly;
			this.li=newly;
		}
		return newly;
	}
	
	public T remove(){
		LinkedListNode<T> tobeRemoved = this.fo;
		this.fo = this.fo.next;
		size--;
		return tobeRemoved.data;
	}
	
}
