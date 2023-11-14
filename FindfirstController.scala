package controllers
import models.FindFirst
import models.Person
import org.mongodb.scala._
import org.mongodb.scala.bson.ObjectId
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class FindFirstController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def findfirst() = Action { implicit request: Request[AnyContent] =>

    val gand:SingleObservable[Person] = new FindFirst().show()
    val cx:String = Await.result(gand.toFuture(), 3 seconds).firstname
    val kx:String = Await.result(gand.toFuture(), 3 seconds).lastname
    val px:ObjectId = Await.result(gand.toFuture(), 3 seconds)._id
    val pbx:List[Any] = List(cx,kx,px)


    //val px:Person = new Person(new ObjectId(),"","")
    Ok(views.html.findfirst(pbx))
  }

  //Connect
}