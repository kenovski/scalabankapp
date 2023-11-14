package models
import org.mongodb.scala.bson.ObjectId

case class Person(val _id:ObjectId,val firstname:String,val lastname:String)
object Person{

	def apply(firstname:String,lastname:String):Person = {

		Person(new ObjectId(),firstname,lastname)
	}
}






