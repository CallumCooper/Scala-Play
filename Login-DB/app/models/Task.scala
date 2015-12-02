package models

/**
  * Created by callumcooper on 02/12/2015.
  */
case class Task(id : Long, label : String)

object Task {

  def all() : List[Task] = Nil

  def create(label : String) {}

  def delete(id : Long) {}

}
