package controllers

import play.api._
import play.api.mvc._
import models.Employee

/**
  * Created by callumcooper on 10/12/2015.
  */
class EmployeeController extends Controller{

  def searchEmployee = Action { implicit request =>
      val employees = Employee.findAll
      Ok(views.html.employees.list(employees))
  }
}
