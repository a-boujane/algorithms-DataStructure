package refreshy.abe.algorithms;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	
	char element;
	boolean isEnd=false;
	Map<Character,TrieNode> children = new HashMap<Character,TrieNode>();	
	
	public TrieNode(char element){
		this.element=element;
	}
	public TrieNode(){};
	
	private TrieNode addKid(char input){
		if(children.containsKey(input))
			return children.get(input);
		else{
			TrieNode kid = new TrieNode(input);
			children.put(input, kid);
			return kid;
		}
	}
	
	TrieNode getKid(char input){
		if(children.containsKey(input)){
			return children.get(input);
		}
		else
			return null;
		
	}
	void addEnd(TrieNode end){
		this.children.put(null, end);
	}
	
	void addCharArray(char[] input){
			TrieNode current=this;
			for(char c:input)
				current=current.addKid(c);
			current.isEnd=true;
	}
	
	void addString(String input){
		TrieNode current=this;	
		for(int i=0;i<input.length();i++)
			current=current.addKid(input.charAt(i));
		current.isEnd=true;
	}
	
	public String toString(){
		return " My value is: "+element+" and am I at the end? : "+isEnd;
	}
	
}
