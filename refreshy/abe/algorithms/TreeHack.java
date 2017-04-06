package refreshy.abe.algorithms;

public class TreeHack {

	int data;
	boolean visited = false;
	TreeHack lefty = null;
	TreeHack righty = null;
	TreeHack parent = null;
	
	public TreeHack(int data){
		this.data=data;
	}
	
	public void setLeft(TreeHack lefty){
		lefty.parent = this;
		this.lefty=lefty;
	}
	public void setRighty(TreeHack righty){
		righty.parent = this;
		this.righty=righty;
	}
	
	public boolean hasLeft(){
		return this.lefty!=null;
	}
	
	public boolean hasRight(){
		return this.righty!=null;
	}
	
	public boolean hasParent(){
		return this.parent!=null;
	}
	
	public boolean isLeftChild(){
		return this.parent.lefty==this;
	}
	
	public boolean isRightChild(){
		return this.parent.righty==this;
	}

}
