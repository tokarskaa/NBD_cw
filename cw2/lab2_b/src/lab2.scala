import bank.BankAccount

object lab2 {
  def main(args: Array[String]) {
    val daysList = List("Monday", "blabla", "Tuesday", "omgomg", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "def not monday")

    println("1 - pattern matching")
    daysList.foreach(day => println(matchDay(day)))

    println("2 - bank account")
    val account1 = new BankAccount()
    val account2 = new BankAccount(21.5)
    println("account 1 balance: " + account1.balance)
    println("account 2 balance: " + account2.balance)

    account1.payment(30)
    account2.payment(30)
    println("account 1 after payment balance: " + account1.balance)
    println("account 2 after payment balance: " + account2.balance)

    account1.withdrawal(10.5)
    account2.withdrawal(10.5)

    println("account 1 after withdrawal balance: " + account1.balance)
    println("account 2 after withdrawal balance: " + account2.balance)

    account1.withdrawal(100)

    println("3 - person pattern matching")
    val persons = List(Person("Jan", "Kowalski"), Person("John", "Smith"), Person("Stanislaw", "Wokulski"), Person("Izabela", "Lecka"), Person("Stanislaw", "Poniatowski"))
    persons.foreach(person => println(sayHi(person)))

    println("4 - function as parameter")
    def add2(v: Int) = v + 2
    println(func(4, add2))

    println("5 - traits")
    object student extends Person2("Jan", "Kowalski") with Student
    object worker extends Person2("John", "Smith") with Worker
    object teacher extends Person2("Mr", "Teacher") with Teacher
    object studentTeacher extends Person2("Student", "Teacher") with Student with Teacher
    object teacherStudent extends Person2("Teacher", "Student") with Teacher with Student
    worker.salary_(2000)
    teacher.salary_(2000)
    studentTeacher.salary_(1000)
    teacherStudent.salary_(2000)
    println("Student tax: " + student.tax)
    println("Worker tax: " + worker.tax)
    println("Teacher tax: " + teacher.tax)
    println("Student teacher tax: " + studentTeacher.tax)
    println("Teacher student tax: " + teacherStudent.tax)
  }

  def matchDay(day: String): String = day match {
    case "Monday" | "Tuesday" | "Wednesday" | "Thursday" | "Friday" => "Work"
    case "Saturday" | "Sunday" => "Weekend"
    case _ => "There is no such day"
  }

  def sayHi(person: Person): String = person match {
    case Person("Stanislaw", lastName) => "Witaj Stanislawie " + lastName
    case Person("Izabela", "Lecka") => "Good morning beautiful Izabela"
    case Person(firstName, lastName) => "Hello stranger"
  }

  def func(value: Int, f: Int => Int): Int = {
    f(f(f(value)))
  }
}

case class Person(val FirstName: String, val LastName: String)

abstract class Person2(val FirstName: String, val LastName: String) {
  def tax: Double
}

trait Worker extends Person2 {
  private var _salary: Double = _
  def salary = _salary
  def salary_(s: Double): Unit = _salary = s

  override def tax: Double = salary*0.2
}

trait Student extends Person2 {
  override def tax = 0
}

trait Teacher extends Worker {
  override def tax: Double = salary*0.1
}

