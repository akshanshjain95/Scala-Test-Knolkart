package Service

import Models.{Item, Photo}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.mutable.Map
import scala.concurrent.Future

class ItemService {

  val PHOTO_SIZE = 28.33
  val RATING = 3.00
  val PRICE = 50.00
  val PRICETWO = 60.00
  val RATINGTWO = 2.00

  val items = Map(
    "electronics" -> List(Item("Item Name", "Electronics Items", Photo("Photo Name", PHOTO_SIZE), "Vendor1", "electronics", RATING, PRICE),
      Item("Item Name2", "Electronics Items", Photo("Photo Name", PHOTO_SIZE), "Vendor1", "electronics", RATINGTWO, PRICETWO))
  )

  def searchItem(category: String, itemName: String): Future[Option[Item]] = {
    Future {
      val itemsInCategory: Option[List[Item]] = items.get(category)
      itemsInCategory.flatMap(listOfItem => listOfItem.find(_.itemName == itemName))
    }
  }

  def sortItems(filter: String): Future[List[Item]] = {
    Future {
      filter match {
        case "price: low to high" => items.values.toList.flatten.sortBy(item => item.price)
        case "price: high to low" => items.values.toList.flatten.sortBy(item => item.price).reverse
        case "rating: low to high" => items.values.toList.flatten.sortBy(item => item.rating)
        case "rating: high to low" => items.values.toList.flatten.sortBy(item => item.rating).reverse
        case _ => List()
      }
    }
  }

}
