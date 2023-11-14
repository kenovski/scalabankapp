package models
import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import models.Helpers._

class Accounts(val name:String,val acctno:String,val phoneno:String,val email:String,val opbal:Double,val balance:Double){


       def create(){

          val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")

// or provide custom MongoClientSettings

         val database: MongoDatabase = mongoClient.getDatabase("egomoto")

         val collection: MongoCollection[Document] = database.getCollection("accounts");
         //val colls: MongoCollection[Document] = database.getCollection("users");

          val doc: Document = Document("name" -> name, "acctno" -> acctno,
                             "phoneno" -> phoneno,"email" -> email,"opbal"->opbal,"balance"->balance)


        val observable: Observable[Completed] = collection.insertOne(doc)


        observable.subscribe(new Observer[Completed] {

        override def onNext(result: Completed): Unit = println("Inserted")

        override def onError(e: Throwable): Unit = println("Failed")

        override def onComplete(): Unit = println("Completed")
})

       }











}
   //val myname = Accounts(name,acctno,phoneno,email,opbal,balance)








