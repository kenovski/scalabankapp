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



case class Info(val name:String,val acctno:String,val phoneno:String,val email:String,val opbal:Double,val balance:Double)



class Deposit(val tofind:String){

 //val ownerRw: ReadWriter[Details] = macroRW[Details]
 val codecRegistry = fromRegistries(fromProviders(classOf[Info]), DEFAULT_CODEC_REGISTRY )


	def finder():SingleObservable[Info] ={

       
          val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")

          // or provide custom MongoClientSettings

         //val database: MongoDatabase = mongoClient.getDatabase("egomoto")
          val database: MongoDatabase = mongoClient.getDatabase("egomoto").withCodecRegistry(codecRegistry)
         val collection: MongoCollection[Info] = database.getCollection("accounts")

            collection.find(equal("name",tofind)).first()
            //upickle.default.write(collection.find(equal("name",tofind)).first())
             
            //collection.find(equal("name",tofind)).first()


	}


}