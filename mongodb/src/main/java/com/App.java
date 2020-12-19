package com;

import java.util.ArrayList;
import java.util.List;

public class App {
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		List<String> list = new ArrayList<String>();
		list.forEach(t -> System.out.println(t));
	}
	
}
