package api

sealed trait ActionResult

case class Success(message: String) extends ActionResult
case class Failure(message: String) extends ActionResult 
