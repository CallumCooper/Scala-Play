package controllers

import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import play.api.mvc.{Action, Controller}
import models.Product
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms.{mapping, longNumber, nonEmptyText}
import play.api.i18n.Messages
import play.api.mvc.Flash

class ProductsController extends Controller {

  // List Function/Controller action
  def list = Action { implicit request =>

    // Get a product list from model
    val products = Product.findAll

    // Render view template
    Ok(views.html.products.list(products))
  }

  def show(ean : Long) = Action { implicit request =>

    // Render a product details page
    Product.findByEan(ean).map { product =>
      Ok(views.html.products.details(product))
    }.getOrElse(NotFound)       // or return a 404 page
  }

  // Save data from the form
  def save = Action { implicit request =>

    // Fill the form with the users input
    val newProductForm = productForm.bindFromRequest()

    newProductForm.fold(

      // If validation fails, re-direct back to Add Page
      hasErrors = { form =>
        Redirect(routes.ProductsController.newProduct()).flashing(Flash(form.data) +
          ("error" -> Messages("validation.errors")))   // Add form data to flash scope with an imformative message
      },

    // If it validates, save new product and redirect to its details page
    success={newProduct =>
      Product.add(newProduct)
      val message = Messages("products.new.success", newProduct.name)
      Redirect(routes.ProductsController.show(newProduct.ean)).flashing("success" -> message)
    }
    )
  }

  // The new product action
  def newProduct = Action { implicit request =>

    // If there is a validation error, bind flash scope data to form
    val form = if (request2flash.get("error").isDefined)
      productForm.bind(request2flash.data)
    else
      productForm
    // Render new product page
    Ok(views.html.products.editProduct(form))
  }

  private val productForm: Form[Product] = Form(
    mapping(

    // The forms fields and their constraints
      "ean" -> longNumber.verifying(
        "validation.ean.duplicate", Product.findByEan(_).isEmpty),
      "name" -> nonEmptyText,
      "description" -> nonEmptyText
    //Functions to map between form and model
    )(Product.apply)(Product.unapply)
  )
}
