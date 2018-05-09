package utils;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Mongo {

    private  static MongoCollection<Document> get_collection(String c_name){
        MongoClient mongo = new MongoClient( "127.0.0.1" , 27017 );
        MongoDatabase db = mongo.getDatabase("rz");
        MongoCollection<Document> cl = db.getCollection("c_name");

        return cl;
    }

    public static void save_test(Document d, String c_name){
        MongoCollection<Document> c = get_collection(c_name);
        c.insertOne(d);
    }
}
