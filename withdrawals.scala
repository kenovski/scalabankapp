package models
import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import models.Helpers._
import java.util.Date


class BankWithDrawals(val date: Date,val name:String,val acctno:String,val amount:Double){


       def create(){

          val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")

// or provide custom MongoClientSettings

         val database: MongoDatabase = mongoClient.getDatabase("egomoto")

         val collection: MongoCollection[Document] = database.getCollection("withdrawals");
         //val colls: MongoCollection[Document] = database.getCollection("users");

          val doc: Document = Document("date" -> date, "name" -> name, "acctno" -> acctno,"amount"-> amount)
                             


        val observable: Observable[Completed] = collection.insertOne(doc)


        observable.subscribe(new Observer[Completed] {

        override def onNext(result: Completed): Unit = println("Inserted")

        override def onError(e: Throwable): Unit = println("Failed")

        override def onComplete(): Unit = println("Completed")
})

       }











}
   //val myname = Accounts(name,acctno,phoneno,email,opbal,balance)








