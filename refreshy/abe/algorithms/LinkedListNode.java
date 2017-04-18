package refreshy.abe.algorithms;

public class LinkedListNode <T> {
	LinkedListNode<T> next=null;
	T data;
	
	public LinkedListNode(T data){
		this.data=data;
	}
	
	public LinkedListNode<T> addToTail(T data){
		LinkedListNode<T> n=this;
		while(n.next!=null)
			n=n.next;
		n.next=new LinkedListNode<T>(data);
		return n.next;
	}
	
	void printLinkedList(){
		LinkedListNode<T> head=this;
		while(head!=null){
			System.out.print(head);
			head=head.next;
		}
		System.out.println(" ");
	}
	
	public String toString(){
		return " -> "+this.data;
		
	}
}
