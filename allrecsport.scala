package models
import models.Person._
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala._
//import org.mongodb._
import models.Helpers._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.bson.codecs.Macros._ 
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY 
import org.bson.codecs.configuration.CodecRegistries.{fromRegistries, fromProviders}


class Support(){

	val codecRegistry = fromRegistries(fromProviders(classOf[Person]), DEFAULT_CODEC_REGISTRY )
     def show():Unit = {
	//val codecRegistry = fromRegistries(fromProviders(classOf[Person]), DEFAULT_CODEC_REGISTRY )
    //val mongoClient: MongoClient = MongoClient()
    val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
    val database: MongoDatabase = mongoClient.getDatabase("persons").withCodecRegistry(codecRegistry)
    val collection: MongoCollection[Person] = database.getCollection("persondetails")
    //val fname = Person.firstname

    val myperson: Person = Person("Mookie","Abaji")
    //myperson
     collection.insertOne(myperson).printResults()
}
   
     
}



object Support{


}

