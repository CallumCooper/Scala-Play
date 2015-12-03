package controllers

import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import models.Task

class ApplicationController extends Controller {

  val taskForm = Form("label" -> nonEmptyText)

  def index = Action {
    Redirect(routes.ApplicationController.tasks)
  }

  def tasks = Action {
    Ok(views.html.index(Task.all(), taskForm))
  }

  def newTask = Action { implicit result =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Task.all(), errors)),
      label => {
        Task.create(label)
        Redirect(routes.ApplicationController.tasks)
      }
    )
  }

  def deleteTask(id : Long) = TODO
}
