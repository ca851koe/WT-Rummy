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
  def rummiAsText = gameController.fieldToString + gameController.rackOfActivePlayerToString + gameController.gameStateToString

  def rummi = Action {
    Ok(rummiAsText)
  }

  def about= Action {
    Ok(views.html.index())
  }
}