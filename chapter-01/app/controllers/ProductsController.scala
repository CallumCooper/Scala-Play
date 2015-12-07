package controllers

import play.api.mvc._
import models.Product

/**
  * Created by callumcooper on 07/12/2015.
  */
class ProductsController extends Controller {

  def list = Action { implicit request =>

    val products = Product.findALL

    Ok(views.html.products.list(products))

  }
}
