package com.pojo;

import org.bson.types.ObjectId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Person {

	private int age;
	private ObjectId id;
	private String name;
	private Address address;

}
