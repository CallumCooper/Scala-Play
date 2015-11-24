package controllers

import play.api.mvc.{Action, Controller}
import models.Product
import play.api.Play.current

class Products extends Controller {

  def list = Action { implicit request =>
    val products = Product.findAll
    Ok(views.html.products.list(products))
  }
}