package Service

import Models.{Item, Photo}
import org.scalatest.AsyncFunSuite

class ItemServiceTest extends AsyncFunSuite {

  val PHOTO_SIZE = 28.33
  val RATING = 3.00
  val PRICE = 50.00

  val itemService = new ItemService
  val item = Item("Item Name", "Electronics Items", Photo("Photo Name", PHOTO_SIZE), "Vendor1", "electronics", RATING, PRICE)

  test("Item Service should return the item searched for")
  {
    itemService.searchItem("electronics", "Item Name").map{
      optionOfItem => assert(optionOfItem.contains(item))
    }
  }

  test("Item Service should return None when searched for non-existing item")
  {
    itemService.searchItem("electronics", "Item Name not exists").map{
      optionOfItem => assert(!optionOfItem.contains(item))
    }
  }

  test("Sort Items should return items sorted by price from low to high")
  {
    itemService.sortItems("price: low to high").map {
      listOfItem => assert(listOfItem.head.price < listOfItem(1).price)
    }
  }

  test("Sort Items should return items sorted by price from high to low")
  {
    itemService.sortItems("price: high to low").map {
      listOfItem => assert(listOfItem.head.price > listOfItem(1).price)
    }
  }

  test("Sort Items should return items sorted by rating from low to high")
  {
    itemService.sortItems("rating: low to high").map {
      listOfItem => assert(listOfItem.head.rating < listOfItem(1).rating)
    }
  }

  test("Sort Items should return items sorted by rating from high to low")
  {
    itemService.sortItems("rating: high to low").map {
      listOfItem => assert(listOfItem.head.rating > listOfItem(1).rating)
    }
  }

  test("Item Service should return an empty list when wrong filter parameter is given")
  {
    itemService.sortItems("invalid filter parameter").map {
      listOfItem => assert(listOfItem.isEmpty)
    }
  }

}
