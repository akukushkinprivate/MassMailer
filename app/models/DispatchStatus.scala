package models

case class DispatchStatus(var id: Long, var status: String) {
  def this() {
    this(0, null)
  }
}
