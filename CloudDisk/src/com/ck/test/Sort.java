package com.ck.test;

public class Sort {
	public static void main(String[] args){
		int[] s={2,3,4,2,5,9,12,5,12,23};
		sort(s);
		for(int i=0;i<s.length;i++){
			System.out.println(s[i]);
		}
	}
	public static void sort(int[] s){
		int temp;
		for(int i=0;i<s.length;i++){
			for(int j=0;j<s.length-i-1;j++){
				if(s[j]>s[j+1]){
					temp=s[j];
					s[j]=s[j+1];
					s[j+1]=temp;
				}
			}
		}
	}
	
}
