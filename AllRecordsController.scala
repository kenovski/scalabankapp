package controllers
//import models.Connect

import javax.inject._
import play.api._
import play.api.mvc._
import org.mongodb.scala._
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import play.api.libs.json
import scala.language.postfixOps

import org.mongodb.scala.bson._
//import org.mongodb.scala.bson.collection.mutable.Document
import models.Allrecords
import models.MyDetails
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class AllRecordsController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.import models.Find
   */
  def all() = Action { implicit request: Request[AnyContent] =>

    val tox:Observable[MyDetails] = Allrecords.show()
    val cx:Seq[MyDetails] = Await.result(tox.toFuture(), 10 seconds)
    //val tox:Seq[Document] = Allrecords.show()
    println(cx)
    Ok(views.html.all(tox,cx))

  }

  //Connect
}
