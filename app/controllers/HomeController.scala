package controllers

import javax.inject._
import play.api.mvc._
import de.htwg.se.rummi.Rummi
import de.htwg.se.rummi.model.{Player}

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  val gameController = Rummi.controller
  gameController.game.generateNewGame(Player("Patrick") :: Player("Carolin") :: Nil)
  gameController.initGame("Patrick" :: "Carolin" :: Nil)

  def rummiAsText = gameController.fieldToString + gameController.rackOfActivePlayerToString +
    gameController.activePlayerToString + gameController.gameStateToString

  def moveTile(command: String) = Action {
    val c = command.split("-%3E")
    gameController.moveTile(c(0), c(1))
    println(c)
    Redirect("/")
  }

  def rummi = Action {
    Ok(views.html.rummikub(gameController))
  }

  def rummiSorted: Action[AnyContent] = Action {
    gameController.sortRack
    Ok(views.html.rummikub(gameController))
  }

  def newTile: Action[AnyContent] = Action {
    gameController.draw
    Ok(views.html.rummikub(gameController))
  }

  def switchPlayer: Action[AnyContent] = Action {
    gameController.switchPlayer
    Ok(views.html.rummikub(gameController))
  }

  def moveTile(from:String, to: String): Action[AnyContent] = Action {
    gameController.moveTile(from, to)
    Ok(views.html.rummikub(gameController))
  }

  def undoStep: Action[AnyContent] = Action {
    gameController.undo
    Ok(views.html.rummikub(gameController))
  }

  def redoStep: Action[AnyContent] = Action {
    gameController.redo
    Ok(views.html.rummikub(gameController))
  }

  def about= Action {
    Ok(views.html.rules())
  }
}