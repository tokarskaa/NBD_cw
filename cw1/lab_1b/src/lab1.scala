import scala.annotation.tailrec

object lab {
  def main(args: Array[String]) {
    val daysList = List("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    val pricesMap = Map("chair" -> 300, "table" -> 500, "lamp" -> 150)

    val tuple = Tuple3("text", 10, 12.5)

    val integerValues = List(100, 0, 12, 23, 0, 0, 2, 3, 0)

    val realValues = List(-6, 12.4, 10.3, 2, 3, -3.5, -1, 0.12)

    println("1a - for")
    println(getListString(daysList))

    println("1b - for, starting with 'T'")
    println(getConditionalListString(daysList, "T"))

    println("1c - while")
    println(getListStringV2(daysList))

    println("2a - recursion")
    println(recursion(daysList))

    println("2b - recursion backwards")
    println(recursionBackwards(daysList))

    println("3 - tail recursion")
    println(tailRecursion(daysList))

    println("4a - foldLeft")
    println(foldLeftConcat(daysList))

    println("4b - foldRight")
    println(foldRightConcat(daysList))

    println("4c - foldLeft starting with T")
    println(foldLeftConcatConditional(daysList, "T"))

    println("5 - collection mapping")
    println(pricesMap.mapValues(_*0.9))

    println("6 - print tuple")
    printTuple(tuple)

    println("7 - option")
    optionExample(pricesMap)

    println("8 - remove zeros")
    println(removeZeros(integerValues))

    println("9 - add one")
    println(addOne(integerValues))

    println("10 - absolute value")
    println(absoluteValueInInterval(realValues))
  }

  def getListString(list: List[String]): String = {
    var concatenated: String = ""
    for (i <- list.indices) {
      if (i!=0)
        concatenated += ", "
      concatenated += list(i)
    }
    concatenated
  }

  def getConditionalListString(list: List[String], firstLetter: String): String = {
    var concatenated: String = ""
    for (i <- list if i.startsWith(firstLetter)) {
      if (concatenated!="")
        concatenated += ", "
      concatenated += i
    }
    concatenated
  }

  def getListStringV2(list: List[String]): String = {
    var i = 0
    var concatenated = ""
    while(i < list.length) {
      if (i!=0)
        concatenated += ", "
      concatenated += list(i)
      i += 1
    }
    concatenated
  }

  def recursion(list: List[String]): String = {
    if (list.isEmpty)
      ""
    else if (list.length==1)
      list.head
    else list.head + ", " + recursion(list.tail)
  }

  def recursionBackwards(list: List[String]): String = {
    if (list.length==1)
      list.head
    else if (!list.isEmpty) {
      recursionBackwards((list.tail)) + ", " + list.head
    }
    else
      ""
  }

  def tailRecursion(list: List[String]): String = {
    @tailrec def concatList(concatenated: String, listToConcat: List[String]): String = {
      if (listToConcat.isEmpty)
        concatenated
      else if (listToConcat.length==1)
        concatList(concatenated + listToConcat.head, listToConcat.tail)
      else
        concatList(concatenated + listToConcat.head + ", ", listToConcat.tail)
    }
    concatList("", list)
  }

  def foldLeftConcat(list: List[String]): String = list.foldLeft[String]("") ((acc, s) => {
    if (acc=="")
      s
    else
      acc + ", " + s
  })

  def foldRightConcat(list: List[String]): String = list.foldRight("") ((acc, s) => {
    if (s=="")
      acc
    else
      acc + ", " + s
  })

  def foldLeftConcatConditional(list: List[String], firstLetter: String): String = list.foldLeft("") ((acc, s) => {
    if (acc=="" && s.startsWith(firstLetter))
      s
    else if (s.startsWith(firstLetter))
      acc + ", " + s
    else
      acc
  })

  def printTuple(tuple: (String, Int, Double)) = {
    println {
      tuple._1 + ", " + tuple._2 + ", " + tuple._3
    }
  }

  def optionExample(map: Map[String, Int]) = {
    println("get chair: " + map.get("chair"))
    println("get sofa: " + map.get("sofa"))

    println("getOrElse(200) chair: " + map.getOrElse("chair", 200))
    println("getOrElse(200) sofa: " + map.getOrElse("sofa", 200))

    println("isEmpty chair: " + map.get("chair").isEmpty)
    println("isEmpty sofa: " + map.get("sofa").isEmpty)
  }

  def removeZeros(list: List[Int]): List[Int] = {
    def loop(list: List[Int], acc: List[Int]): List[Int] = list match{
      case Nil => acc
      case 0 :: tail => loop(tail, acc)
      case head :: tail => loop(tail, acc :+ head)
    }
    return loop(list, List())
  }

  def addOne(list: List[Int]): List[Int] = {
    return list.map(_+1)
  }

  def absoluteValueInInterval(list: List[Double]): List[Double] = {
    return list.filter(x => (x >= -5 && x <= 12)).map(_.abs)
  }
}


