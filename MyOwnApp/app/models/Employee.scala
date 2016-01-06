package models

/**
  * Created by callumcooper on 09/12/2015.
  */
case class Employee(employeeUsername : String, employeePassword : String)

object Employee {

  // Group of employees which is dummy data, can be associated
  // as a dummy db.
  var employees = Set(
    Employee("jmann", "jm"),
    Employee("ccooper", "cc"),
    Employee("dbeckhame", "db"),
    Employee("pMaldini", "pm")
  )

  // Finder method
  def findAllEmployees = employees.toList.sortBy(_.employeeUsername)

  // Add employee
  def addEmployee(emp : Employee) : Unit = {
    employees = employees + emp
  }
}
