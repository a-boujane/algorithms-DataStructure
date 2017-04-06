package refreshy.abe.algorithms;

public class MinHeapRoot {
	
	int size=1;
	int[] elements=new int[1];
	
	public MinHeapRoot(int root){
		this.elements[0]=root;
	}
	
	
	
	
	
	
	public void addEnd(int last){
		ensureSize();
		size++;
		int child = size-1;
		elements[child]=last;
		while(!validateParent(child)){
			int newKid = getParentIndex(child);
			swap(child,newKid);
			child=newKid;
		}
	}
	

	
	public int findIndex(int element){
		int index = 0;
		MyQueue<Integer> q = new MyQueue<Integer>();
		q.addElement(0);
		while(!q.isEmpty()){
			index = q.removeElement();
			
			try{
				if(elements[index]==element)
				return index;
			if(elements[index]<element){
					q.addElement(this.getLeftChildIndex(index));
					q.addElement(this.getRightChildIndex(index));
			}
			}
				
				catch(ArrayIndexOutOfBoundsException e){
				}
			}
		return -1;
	}
	
	public boolean validateParent(int child){
		if(elements[child]<getParent(child))
			return false;
		return true;
	}
	
	public void swap(int index1, int index2){
		int temp = elements[index1];
		elements[index1]=elements[index2];
		elements[index2]=temp;
	}
	
	public int getParentIndex(int child){
		return (child-1)/2;
	}
	
	public int getLeftChildIndex(int parent){
		return parent*2+1;
	}
	
	public int getRightChildIndex(int parent){
		return parent*2+2;
	}
	public int getParent(int child){
		return elements[getParentIndex(child)];
	}
	
	public int getLeftChild(int parent){
		int child = getLeftChildIndex(parent);
		return child<size? elements[child]:-1;
	}
	
	public int getRightChild(int parent){
		int child = getRightChildIndex(parent);
		return child<size? elements[child]:-1;
	}
	
	public void ensureSize(){
		if(size+1>elements.length){
			int newElements[] = new int[elements.length*2];
			for(int i=0;i<elements.length;i++){
				newElements[i]=elements[i];
			}
			this.elements=newElements;
		}
	}
	
}
