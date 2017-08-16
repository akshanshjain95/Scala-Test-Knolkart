package main.scala.Services

import main.scala.Models.User
import org.mindrot.jbcrypt.BCrypt
import scala.collection.mutable.Map
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class AccountsService {

  val MOBILE_NUMBER = 9999819877L
  val AGE = 21

  val accounts = Map(
    MOBILE_NUMBER -> User("Akshansh", MOBILE_NUMBER, "B-62, Sector-56, Noida", AGE, "Akshansh95", "akshansh123")
  )

  def addUser(user: User): Future[Boolean] = {
    Future {
      if (accounts.contains(user.mobileNumber)) {
        false
      }
      else {
        accounts += user.mobileNumber -> user
        true
      }
    }
  }

  def authenticate(username: String, password: String): Future[(String, Boolean)] = {
    Future {
     val userAccount = accounts.filter{
       user => user._2.username == username && user._2.password == password
     }
      if(userAccount.isEmpty)
        {
          ("Cannot login you with the given details.", false)
        }
      else
        {
          (BCrypt.hashpw(userAccount.head._1.toString, BCrypt.gensalt()), true)
        }
    }
  }
}
