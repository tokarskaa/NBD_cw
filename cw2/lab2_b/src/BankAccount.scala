package bank

class BankAccount {
  private var _balance: Double = 0
  def balance = _balance

  def this(balance: Double = 0) {
    this()
    _balance = balance
  }

  def payment(value: Double) = {
    _balance += value
  }

  def withdrawal(value: Double): Any = {
    if (_balance - value < 0) {
      println("You cannot withdraw over current balance")
    } else {
      _balance -= value
    }
  }
}
