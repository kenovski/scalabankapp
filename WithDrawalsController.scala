package controllers
//import models.Connect
import org.mongodb.scala._
import javax.inject._
import play.api._
import play.api.mvc._
import models.Info
import models.Deposit
import models.Update
import models.Pinfo
import models.BankDeposits
import models.BankWithDrawals
import models.Audits
//import models.Limits
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps
import org.mongodb.scala.model.Updates._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class WithDrawalsController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def withdrawals() = Action { implicit request: Request[AnyContent] =>

   val tino:Option[Map[String, Seq[String]]]  = request.body.asFormUrlEncoded
    val cname  = tino.get("cname").head
    
    val amt = tino.get("amt").head.toDouble

    val gand:SingleObservable[Info] = new Deposit(cname).finder()
    val cx:Double = Await.result(gand.toFuture(), 3 seconds).balance
    val acctno:String = Await.result(gand.toFuture(), 3 seconds).acctno
    
    val difx:Double = cx - amt

    //new Update(cname,amt,difx).finder()
    //val millis:Long =System.currentTimeMillis();  
    //val date:java.sql.Date = new java.sql.Date(millis);
    //new Limits().check(cx,amt)

    
  if(cx > amt){
     
     new Update(cname,amt,difx).finder()
     val millis:Long =System.currentTimeMillis();  
     val date:java.sql.Date = new java.sql.Date(millis);
     new BankWithDrawals(date,cname,acctno,amt).create()
     new Audits(date,cname,acctno,0.0,amt,difx).create()
    

  }


  else{

    println("Insufficient balance" +cx)
  }

    //new BankWithDrawals(date,cname,acctno,amt).create()
    //new Audits(date,cname,acctno,0.0,amt,difx).create()
    

   
   
    

    Ok(views.html.withdrawals(tino,cx,difx))
    //Ok("Done or nothing")
  }

  
  
}
