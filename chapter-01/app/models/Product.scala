package models

/**
  * Created by callumcooper on 07/12/2015.
  */
case class Product(ean : Long, name : String, description : String)

object Product {

  var products = Set(
        Product(5010255079763L, "Paperclips Large",
                "Large Plain Pack of 1000"),
        Product(5018206244666L, "Giant Paperclips",
                "Giant Plain 51mm 100 pack"),
        Product(5018306332812L, "Paperclip Giant Plain",
                "Giant Plain Pack of 10000"),
        Product(5018306312913L, "No Tear Paper Clip",
                "No Tear Extra Large Pack of 1000"),
        Product(5018206244611L, "Zebra Papercips",
                "Zebra Length 28mm Assorted 150 Pack")
  )

  def findALL = products.toList.sortBy(_.ean)

}
