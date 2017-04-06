package refreshy.abe.algorithms;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AngruChildren {
	    
	    static int[] arr = {1,2,3,4,10,20,30,40,100,200};
	    
	     public static void solve( int n, int k) {
	         long result = 0;
	    	 int start =0;
	         long sum=0;
	    	 for(int i=0;i<k;i++){
	             sum+=arr[i];
	    		 result+=((long)arr[i]*(long)(2*i-k+1));
	    	 }
	    	 
	    	 long min = result;
	    	 long temp=result;;
	    	 while(start<n-k){
	    		 temp=updateDistance(temp, sum, start, start+k);
	    		 if(temp<min)
	    			 min=temp;
	             sum=sum+arr[start+k]-arr[start];
	    		 start++;
	    	 }
	    	 
	    	 System.out.println(min);
	    	 
	     }
	    
	    public static long updateDistance( long value, long sum, int start, int l){
	    	
	        value-=sum;
	        value-=sum;
	        value+=arr[start];
	        value+=arr[start];
	        int i=(l-start-1);
	        while(i>0){
	        	value+=(arr[start]+arr[l]);
	        	i--;
	        }
	    	 
	    	 return value;
	     }

     

    public static void main(String[] args)  throws Exception {
         int N = 10;
         int K = 4;
         solve( N, K);
    }
}
