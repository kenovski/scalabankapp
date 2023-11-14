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


class FindFirst(){

	//val codecRegistry = fromRegistries(fromProviders(classOf[Person]), DEFAULT_CODEC_REGISTRY )
     def show():SingleObservable[Person] = {
	val codecRegistry = fromRegistries(fromProviders(classOf[Person]), DEFAULT_CODEC_REGISTRY )
    //val mongoClient: MongoClient = MongoClient()
    val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
    val database: MongoDatabase = mongoClient.getDatabase("persons").withCodecRegistry(codecRegistry)
    val collection: MongoCollection[Person] = database.getCollection("persondetails")
    
    collection.find().first().printHeadResult()
    
       collection.find().first()

    
}
   
     
}



object FindFirst{


}

