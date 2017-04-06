package refreshy.abe.algorithms;

public class LinkedListNode {
	LinkedListNode next=null;
	int data;
	
	public LinkedListNode(int data){
		this.data=data;
	}
	
	public LinkedListNode addToTail(int data){
		LinkedListNode n=this;
		while(n.next!=null)
			n=n.next;
		n.next=new LinkedListNode(data);
		return n.next;
	}
	
	void printLinkedList(){
		LinkedListNode head=this;
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
