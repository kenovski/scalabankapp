package controllers
//import models.Connect

import javax.inject._
import play.api._
import play.api.mvc._
import models.Accounts

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class AccountsController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def accounts() = Action { implicit request: Request[AnyContent] =>

   val tino:Option[Map[String, Seq[String]]]  = request.body.asFormUrlEncoded
    val name  = tino.get("pname").head
    val acctno = tino.get("acctno").head
    val email = tino.get("email").head
    val opbal = tino.get("opbal").head.toDouble
    val bal = tino.get("bal").head.toDouble
    val phone = tino.get("phoneno").head

   new Accounts(name,acctno,phone,email,opbal,bal).create()
   
    

    Ok(views.html.accounts(tino))
  }

  //Connect
}
