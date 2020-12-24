package com.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;

public class InsertMany {

	// private static final Random rand = new Random();

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
			MongoCollection<Document> collection = sampleTrainingDB.getCollection("test_insert_many");
			
			// Document doc = new Document("_id", new ObjectId());
			// Document doc = new Document();
			// doc.append("text", RandomStringUtils.randomAlphabetic(5).toUpperCase());
			// collection.insertOne(doc);
			
			List<Document> list = new ArrayList<>();
			for (int i = 1; i < 10; i++) {
				Document doc = new Document();
				doc.append("text", RandomStringUtils.randomAlphabetic(5).toUpperCase());
				list.add(doc);
			}
			String _id = collection.find().first().get("_id").toString();
			Document first = new Document("_id", _id).append("text", RandomStringUtils.randomAlphabetic(5).toUpperCase());
			list.add(first);
			collection.insertMany(list);
			// collection.insertMany(list, new InsertManyOptions().ordered(true));
			
			System.out.println("OK!");
		}

	}

}
