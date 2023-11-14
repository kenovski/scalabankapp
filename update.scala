package models
import org.mongodb.scala._
//import org.mongodb._
import org.mongodb.scala.model.Filters._
import models.Helpers._
import upickle.default._
import play.api.libs.json._
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.bson.codecs.Macros._ 
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY 
import org.bson.codecs.configuration.CodecRegistries.{fromRegistries, fromProviders}
import org.mongodb.scala.model.Updates._



case class Pinfo(val name:String,val acctno:String,val phoneno:String,val email:String,val opbal:Double,val balance:Double)



class Update(val tofind:String,val amt:Double,val bal:Double){

 //val ownerRw: ReadWriter[Details] = macroRW[Details]
 val codecRegistry = fromRegistries(fromProviders(classOf[Pinfo]), DEFAULT_CODEC_REGISTRY )


	def finder():Unit ={

       
          val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")

          // or provide custom MongoClientSettings

         //val database: MongoDatabase = mongoClient.getDatabase("egomoto")
          val database: MongoDatabase = mongoClient.getDatabase("egomoto").withCodecRegistry(codecRegistry)
         val collection: MongoCollection[Pinfo] = database.getCollection("accounts")

            collection.updateOne(equal("name",tofind), set("balance",bal)).printHeadResult("Updated")


	}


}