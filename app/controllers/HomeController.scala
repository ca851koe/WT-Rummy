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


  def rummi = Action {
    Ok(rummiAsText)
  }

  def rummiSorted: Action[AnyContent] = Action {
    gameController.sortRack
    Ok(rummiAsText)
  }

  def newTile: Action[AnyContent] = Action {
    gameController.draw
    Ok(rummiAsText)
  }

  def switchPlayer: Action[AnyContent] = Action {
    gameController.switchPlayer
    Ok(rummiAsText)
  }

  def moveTile(from:String, to: String): Action[AnyContent] = Action {
    gameController.moveTile(from, to)
    Ok(rummiAsText)
  }

  def undoStep: Action[AnyContent] = Action {
    gameController.undo
    Ok(rummiAsText)
  }

  def redoStep: Action[AnyContent] = Action {
    gameController.redo
    Ok(rummiAsText)
  }

  def about= Action {
    Ok(views.html.index())
  }

  def start = Action {
    Ok(views.html.index())
  }

  def rules = Action {
    Ok(views.html.rules())
  }
}