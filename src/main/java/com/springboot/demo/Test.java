package com.springboot.demo;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
	private static ObjectMapper om = new ObjectMapper();
	
	public static void main(String[] args) throws IOException {
		String json = "{\"name\":\"zhangsan\",\"age\":18}";
		System.err.println(json + " === " + om.readValue(json, User.class).getName());
		
		String json2 = "\"{\\\"name\\\":\\\"zhangsan\\\",\\\"age\\\":18}\"";
		json2 = json2.substring(0, json2.length() - 1).replaceFirst("\"", "").replaceAll("\\\\", "");
		System.out.println("==> " + json2);
		System.out.println("~~> " + om.readValue(json2, User.class).getName());

		
	}

}

class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6744944380563065810L;
	private String name = "zhangsan";
	private int age = 18;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
