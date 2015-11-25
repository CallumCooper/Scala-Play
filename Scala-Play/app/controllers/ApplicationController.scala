package controllers

import play.api.mvc.{Action, Controller}

class ApplicationController extends Controller {

  def index = Action {
    // Redirect to products list URL
    Redirect(routes.ProductsController.list())
  }
}
