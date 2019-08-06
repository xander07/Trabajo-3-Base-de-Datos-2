package Connections;


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
    this.mostrarTClientes();
  }

  public final void mostrarTClientes() {
    List<Document> datosClientes = new ArrayList<>();
    totalesCliente.find().into(datosClientes);
    datosClientes.forEach((obj) -> {
      System.out.println(obj.toString());
    });
  }
}
