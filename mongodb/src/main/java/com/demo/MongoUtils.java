package com.demo;

import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoIterable;

public class MongoUtils {

	private static final String HOST = "localhost";
	private static final int PORT = 27017;

	// private static final String USERNAME = "mgdb";
	// private static final String PASSWORD = "1234";

	// Cách kết nối vào DB MongoDB có bảo mật
	// private static MongoClient getMongoClient_2() {
	// MongoCredential credential =
	// MongoCredential.createMongoCRCredential(USERNAME, MyConstants.DB_NAME,
	// PASSWORD.toCharArray());
	// return new MongoClient(new ServerAddress(HOST, PORT),
	// Arrays.asList(credential));
	// }

	// Cách kết nối vào MongoDB không bắt buộc bảo mật
	private static MongoClient getMongoClient_1() throws UnknownHostException {
		return new MongoClient(HOST, PORT);
	}

	public static MongoClient getMongoClient() throws UnknownHostException {
		// Kết nối vào MongoDB không bắt buộc bảo mật
		return getMongoClient_1();
		// Bạn có thể thay thế bởi getMongoClient_2()
		// trong trường hợp kết nối vào MongoDB có bảo mật
	}

	public static void ping() {
		try {
			MongoClient mongoClient = getMongoClient();
			// Danh sách các DB Names
			MongoIterable<String> iterable = mongoClient.listDatabaseNames();
			System.out.println("List all DB:");
			List<String> list = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
			list.forEach(t -> System.out.println("+ DB Name: " + t));
			System.out.println("Done!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnknownHostException {
		// ping();
	}

}
