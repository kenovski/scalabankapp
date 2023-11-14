package models
import org.mongodb.scala._
//import org.mongodb._
import org.mongodb.scala.model.Filters._
import models.Helpers._
import upickle.default._
import play.api.libs.json._
//import models.Konnect

import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.bson.codecs.Macros._ 
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY 
import org.bson.codecs.configuration.CodecRegistries.{fromRegistries, fromProviders}

case class MyDetails(val name:String,val acctno:String,val phoneno:String,val email:String,val opbal:Double,val balance:Double)


object Allrecords{


	  def show():Observable[MyDetails] = {
          val codecRegistry = fromRegistries(fromProviders(classOf[MyDetails]), DEFAULT_CODEC_REGISTRY )
        //new Konnect().connect()
        val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
        //val database: MongoDatabase = mongoClient.getDatabase("egomoto")
        val database: MongoDatabase = mongoClient.getDatabase("egomoto").withCodecRegistry(codecRegistry)
        val collection: MongoCollection[MyDetails] = database.getCollection("accounts")
	  	collection.find()
	  }
}