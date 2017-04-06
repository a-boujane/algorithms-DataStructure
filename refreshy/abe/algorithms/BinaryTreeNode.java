package refreshy.abe.algorithms;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeNode<T extends Comparable<T>> {
	
	T data;
	BinaryTreeNode<T> lefty;
	BinaryTreeNode<T> righty;
	BinaryTreeNode<T> daddy;
	
	public BinaryTreeNode(T data){
		this.data=data;
		this.lefty=null;
		this.righty=null;
		this.daddy = null;
	}
	
	public void addChild(BinaryTreeNode<T> child){
		if(child.data.compareTo(this.data)<0){
			if(lefty==null){
				lefty=child;
				lefty.daddy=this;
			}
			else
				lefty.addChild(child);
		}
		else{
			if(righty==null){
				righty=child;
				righty.daddy=this;
			}
			else
				righty.addChild(child);
		}
	}
	
	public List<BinaryTreeNode<T>> getKids(){
		
		List<BinaryTreeNode<T>> result = new ArrayList<BinaryTreeNode<T>>();
		if(this.lefty!=null)
			result.add(this.lefty);
		if(this.righty!=null)
			result.add(this.righty);
		return result;
	}
	
	public boolean isMyDad(BinaryTreeNode<T> daddy){
		return daddy==this.daddy;
	}
	
	
	
}
