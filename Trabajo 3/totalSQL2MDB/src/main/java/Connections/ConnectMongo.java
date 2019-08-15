package Connections;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.bson.Document;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

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

  public MongoCursor<Document> getTotalesCliente() {
    return totalesCliente.find().iterator();
  }

  public MongoCursor<Document> getTotalesVendedor() {
    return totalesVendedor.find().iterator();
  }

  public void saveCliente(int codigoCliente, int totalComprado) {
    Document clientInfo = new Document();
    clientInfo.put("codigocliente", codigoCliente);
    clientInfo.put("totalcomprado", totalComprado);
    totalesCliente.updateOne(Filters.eq("codigocliente", codigoCliente), new Document("$set", clientInfo), new UpdateOptions().upsert(true));
  }
  
  public void saveVendedor(int codigoVendedor, int totalVendido) {
    Document vendedorInfo = new Document();
    vendedorInfo.put("codigovendedor", codigoVendedor);
    vendedorInfo.put("totalvendido", totalVendido);
    totalesVendedor.updateOne(Filters.eq("codigovendedor", codigoVendedor), new Document("$set", vendedorInfo), new UpdateOptions().upsert(true));
  }
}
