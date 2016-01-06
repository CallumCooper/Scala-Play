package controllers

import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import play.api.mvc.{Action, Controller}
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms.{mapping, longNumber, nonEmptyText}
import play.api.i18n.Messages
import play.api.mvc.Flash
import models.Employee

/**
  * Created by callumcooper on 09/12/2015.x
  */
class EmployeesController extends Controller{

  // List of employees controller
  def list = Action { implicit request =>
    val employees = Employee.findAllEmployees
    Ok(views.html.employees.listOfEmployees(employees))
  }
}
