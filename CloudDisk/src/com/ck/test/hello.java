package com.ck.test;
public class hello  {

	 public static void main(String args[]){

		    int[] values={3,2,6,2,12,0,7,67,5};

		    sort(values);

		    for(int i=0;i<values.length;i++){//排序后打印数组中的元素

		      System.out.println("Index: "+i+"  value: "+values[i]);

		    }
System.out.println(values);
		  }

		  public static void sort(int[] values){

		    int temp;

		    for(int i=0;i<values.length;i++){//趟数

		      for(int j=0;j<values.length-i-1;j++){//比较次数

		        if(values[j]>values[j+1]){

		          temp=values[j];

		          values[j]=values[j+1];

		          values[j+1]=temp;

		        }

		      }

		    }

		  }


}