package models
import org.mongodb.scala._
import org.mongodb._
import org.mongodb.scala.model.Filters._
import models.Helpers._
import upickle.default._
import play.api.libs.json._

class Konnect(){

	def connect(){

		val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")

          // or provide custom MongoClientSettings

         val database: MongoDatabase = mongoClient.getDatabase("egomoto")

         //val collection: MongoCollection[Document] = database.getCollection("accounts")

         
	}
}


object Konnect {


}