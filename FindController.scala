package controllers
//import models.Connect

import javax.inject._
import play.api._
import play.api.mvc._
import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import models.Find
import models.Details
//import models.Details

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class FindController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def finder() = Action { implicit request: Request[AnyContent] =>

    val tino:Option[Map[String, Seq[String]]]  = request.body.asFormUrlEncoded
    val name  = tino.get("name").head
    val pox:SingleObservable[Details] = new Find(name).finder()
    //val rets:SingleObservable[Document] = Option[None]

    Ok(views.html.find(pox))
  }

  //Connect
}