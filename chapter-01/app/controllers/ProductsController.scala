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

/**
  * Created by callumcooper on 07/12/2015.
  */
class ProductsController extends Controller {

  def list = Action { implicit request =>
    val products = Product.findALL
    Ok(views.html.products.list(products))
  }

  def show(ean : Long) = Action { implicit request =>

    Product.findByEan(ean).map { product =>
      Ok(views.html.products.details(product))
    }.getOrElse(NotFound)
  }

  private val productForm : Form[Product] = Form(
    mapping(
      "ean" -> longNumber.verifying(
        "validation.ean.duplicate", Product.findByEan(_).isEmpty),
      "name" -> nonEmptyText,
      "description" -> nonEmptyText
    )(Product.apply)(Product.unapply)
  )

  def save = Action { implicit request =>
    val newProductForm = productForm.bindFromRequest()

    newProductForm.fold(

      hasErrors = { form =>
      Redirect(routes.ProductsController.newProduct()).flashing(Flash(form.data) +
        ("error" -> Messages("validation.errors")))
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

}
