package Services

import main.scala.Models.User
import main.scala.Services.AccountsService
import org.scalatest.AsyncFunSuite

class AccountsServiceTest extends AsyncFunSuite {

  val newUser = User("Rahul", 9999819878L, "B-63, Sector-56, Noida", 21, "Rahul95", "rahul123")
  val existingUser = User("Akshansh", 9999819877L, "B-62, Sector-56, Noida", 21, "Akshansh95", "akshansh123")
  val accountService = new AccountsService

  test("Accounts Service should add the given user")
  {
    accountService.addUser(newUser).map(isRegistered => assert(isRegistered))
  }

  test("Accounts Service should not be able to add existing user")
  {
    accountService.addUser(existingUser).map(isRegistered => assert(!isRegistered))
  }

  test("Accounts Service should be able to authenticate an existing user")
  {
    accountService.authenticate("Akshansh95", "akshansh123").map{
      (result) =>
        assert(result._2)
    }
  }

  test("Accounts Service should not be able to authenticate a non-existing user")
  {
    accountService.authenticate("Akshansh", "akshansh").map{
      (result) =>
        assert(!result._2)
    }
  }

}
