package com.demo;

import org.bson.json.JsonWriterSettings;

public class MyConstants {

	// Có thể Database này không tồn tại trên MongoDB của bạn
	// Nhưng nó sẽ được tự động tạo ra, không cần phải thay đổi trường này
	public static final String DB_NAME = "mongodb://localhost:27017";
	
	public static final JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

}
