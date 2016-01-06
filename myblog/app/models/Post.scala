package models

import play.api.libs.json.Json

/**
  * Created by callumcooper on 07/12/2015.
  */
case class Post(title : String, body : String)

object JsonFormats {
  implicit val postFormat = Json.format[Post]
}
