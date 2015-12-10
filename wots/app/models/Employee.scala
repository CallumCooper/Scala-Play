package models

/**
  * Created by callumcooper on 10/12/2015.
  */
case class Employee(username : String, password : String)

object Employee {

  var employees = Set(
        Employee("alstock", "thegaffer"),
        Employee("benback", "armyLad212"),
        Employee("debbiethorne", "HRLass")
  )

  def findAll = employees.toList.sortBy(_.username)
}