package controllers

import models._
import play.api._
import play.api.libs.json.{JsArray, Json}
import play.api.mvc._
import JsonFormats._
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.Cursor

class Application extends Controller with MongoController {

  def index = Action {
    Ok(views.html.index("API 1.0"))
  }

  def collection : JSONCollection = db.collection[JSONCollection]("post")

  def findAll = Action.async {
    val cursor : Cursor[Post] = collection.find(Json.obj()).cursor[Post]

    val futurePostsList : Future[List[Post]] = cursor.collect[List]()

    val futurePostsJsonArray : Future[JsArray] = futurePostsList.map { posts =>
      Json.arr(posts)
    }

    futurePostsJsonArray.map { posts =>
      Ok(posts)
    }
  }
}
