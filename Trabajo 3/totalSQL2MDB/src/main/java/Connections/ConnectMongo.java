package Connections;


import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.*;
import org.bson.Document;
import com.mongodb.client.*;

/**
 *
 * @author moonzenith
 */
public class ConnectMongo {
 
  MongoClient client;
  MongoDatabase db;
  MongoCollection totalesCliente;
  MongoCollection totalesVendedor;

  public ConnectMongo() {
    client = new MongoClient(new MongoClientURI("mongodb://adminDB2:Un4B3ll3z4@moonzenith.2waky.com:6969/?authSource=admin"));
    db = client.getDatabase("trabajo3");
    totalesCliente = db.getCollection("totalescliente");
    totalesVendedor = db.getCollection("totalesvendedor");
  }

  public void saveCliente() {
    Document document = new Document();
    document.put("codigocliente", 1);
    document.put("totalcomprado", 100000);
    totalesCliente.insertOne(document);
  }
}
