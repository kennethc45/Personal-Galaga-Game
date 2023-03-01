package cs2.game

import scalafx.application.JFXApp
import scalafx.scene.canvas.Canvas
import scalafx.scene.Scene
import cs2.util.Vec2
import scalafx.animation.AnimationTimer
import scalafx.scene.paint.Color
import scalafx.scene.image.Image
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import scalafx.Includes._
import scala.collection.mutable.Buffer
import scala.collection.immutable.Set
import javafx.scene.text.Font
import scala.collection.mutable.Stack

/** main object that initiates the execution of the game, including construction
 *  of the window.
 *  Will create the stage, scene, and canvas to draw upon. Will likely contain or
 *  refer to an AnimationTimer to control the flow of the game.
 */

 class Gamestate(var p:Player, var es:EnemySwarm, var b:Buffer[Bullet], var eb:Buffer[Bullet], var lives:Int, var score:Int) {

}

object SpaceGameApp extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Galaga!"
    scene = new Scene(1000,1000) {
      val canvas = new Canvas(1000,1000)
      content = canvas
      val g = canvas.graphicsContext2D

      val bulletPath = getClass().getResource("/images/bullet.png")
      val bulletImg = new Image(bulletPath.toString)

      val playerPath = getClass().getResource("/images/Player.png")
      val playerImg = new Image(playerPath.toString)
      var player = new Player(playerImg, new Vec2(350,700), bulletImg, 200, 150) //Player Instance

      var bullet = new Bullet (bulletImg, player.pos, new Vec2 (0, -0.5), 70, 100) //Bullet Instance
      var bulletList:Buffer[Bullet] = Buffer() //Player's Bullets Buffer

      val enemyPath = getClass().getResource("/images/Gabe.png")
      val enemyImg = new Image(enemyPath.toString)
      var enemy = new Enemy(enemyImg, new Vec2(350, 150), bulletImg, 200, 250) //Enemy Instance
      var enemyBulletList:Buffer[Bullet] = Buffer() //Enemy's Bullets Buffer
      var enemySwarm = new EnemySwarm(3, 3, 1000, 1000)
      enemySwarm.addSwarm()

      var keyCode:Set[KeyCode] = Set() //Set of Player Movement

      canvas.onKeyPressed = (e:KeyEvent) => {
        keyCode += e.code
      }

       canvas.onKeyReleased = (e:KeyEvent) => {
        keyCode -= e.code
      }

      var delay = 200
      var count = 0

      var shootDelay = 200.0

      var showStartScreen = true 
      var playerScore = 0
      var playerLives = 5

      var gameHistory = new Stack[Gamestate]
      //var currentState = new Gamestate(player.clone(), enemySwarm.clone(), bulletList.clone(), enemyBulletList.clone(), playerLives, playerScore)
      //gameHistory.push(currentState)

      val timer = AnimationTimer(t => {                       //Animation Timer
        if (showStartScreen) { //Game Start Screen
          g.setFill(Color.White)
          g.fillRect(0,0, 1000, 1000) 
          g.setFont(new Font("arial", 100))
          g.setFill(Color.Black)
          g.fillText("Welcome to ACM", 150, 400)
          g.fillText("Press Space", 250, 600)
          if (keyCode.contains(KeyCode.Space)) {
            showStartScreen = false 
          }
        } else 
        if (playerLives == 0) { //End Game Screen
          g.setFill(Color.White)
          g.fillRect(0,0, 1000, 1000) 
          g.setFont(new Font("arial", 100))
          g.setFill(Color.Black)
          g.fillText("You died to Gabe?", 100, 200)
          g.fillText("Final Score:" + playerScore, 100, 400)
          g.fillText("Press Space to", 100, 600)
          g.fillText("Try Again", 200, 800)
          if (keyCode.contains(KeyCode.Space)) {
            playerScore = 0
            playerLives = 5
            enemySwarm.removeSwarm()
            enemySwarm.addSwarm  
            var toRemove6:Buffer[Bullet] = Buffer() //Removes on screen player's bullets
              for (e <- bulletList) {
                toRemove6 += e
              }
              bulletList --= toRemove6

            var toRemove7:Buffer[Bullet] = Buffer() //Removes on screen enemy's bullets
              for (e <- enemyBulletList) {
                toRemove7 += e
              }
              enemyBulletList --= toRemove7
            
          }

        } else {

        var reverse = false 

        if (keyCode.contains(KeyCode.R)) {
            reverse = true 
          } else {
            reverse = false 
          }

        if (reverse && gameHistory.isEmpty == false){
          var rev = gameHistory.pop()
          player = rev.p 
          enemySwarm = rev.es 
          bulletList = rev.b
          enemyBulletList = rev.eb
          playerLives = rev.lives
          playerScore = rev.score

        } else 

          gameHistory.push(new Gamestate(player.clone(), enemySwarm.clone(), bulletList.clone(), enemyBulletList.clone(), playerLives, playerScore))

          g.setFill(Color.White)
          g.fillRect(0,0, 1000, 1000)

          g.setFont(new Font("arial", 25))
          g.setFill(Color.Black)
          g.fillText( "Score:" + playerScore, 100, 100) //Displaying Score Tracker
          g.fillText( "Lives:" + playerLives, 100, 150) //Displaying Player's Lives

          player.display(g)
          //enemy.display(g)  //To remove original (single) Gabe            REMOVE IF TEST DOESN'T WORK
          enemySwarm.display(g)

          for (e <- enemyBulletList) {
            e.timeStep()
            e.display(g)
          }

          for (b <- bulletList) {
            b.timeStep()
            b.display(g)
          }
          
          var toRemove:Buffer[Bullet] = Buffer() // Removes off screen bullets
          for (b <- bulletList){
            if(b.isDead()) {
              toRemove += b
            }
          }
          bulletList --= toRemove

          if (count > delay) { //Restricts enemy fire rate
            count = 0
            //bulletList ::= enemy.shoot() --To remove original (single) Gabe
            enemyBulletList += enemySwarm.shoot()

          }
            else count +=1

          shootDelay -= 1 

            if(((keyCode.contains(KeyCode.Space)) && shootDelay <= 0)) { //Player Movement Events
            bulletList += player.shoot() 
            shootDelay = 100 
            }
            if((keyCode.contains(KeyCode.Left)) || (keyCode.contains(KeyCode.A))) {
            player.moveLeft()
            }
            if((keyCode.contains(KeyCode.Right)) || (keyCode.contains(KeyCode.D))) {
            player.moveRight()
            }
            if((keyCode.contains(KeyCode.Up)) || (keyCode.contains(KeyCode.W))) {
            player.moveUp()
            }
            if((keyCode.contains(KeyCode.Down)) || (keyCode.contains(KeyCode.S))) {
            player.moveDown()
            }

          var toRemove2:Buffer[Bullet] = Buffer()
          for (b <- enemyBulletList) { //If the player hits an enemy bullet, then respawn to original position
            if(player.collision(b)){ 
              player.moveTo(new Vec2(350,700)) 
              toRemove2 += b
              playerLives -= 1 //Removes a player life
            }
          } 
          enemyBulletList --= toRemove2
          
          var toRemove3:Buffer[Bullet] = Buffer()
          for (e <- enemyBulletList) { //If an enemy's bullet hits a player's bullet, then it disappears 
            for (b <- bulletList) {
              if(e.collision(b)) {
                toRemove3 += e
                toRemove3 += b
              }
            }
          }
          enemyBulletList --= toRemove3
          bulletList --= toRemove3

          var toRemove4:Buffer[Enemy] = Buffer()
          for (e <- enemySwarm.swarm) { //If the player hits an enemy, then respawn to original position
            if(player.collision(e)){ 
              player.moveTo(new Vec2(350,700))
            }
          } 

          var toRemove5:Buffer[Bullet] = Buffer()
          for (b <- bulletList) { //Checks if any of the swarm got hit by a bullet (ENEMY REMOVEL METHOD IN ENEMYSWARM CLASS)
            if (enemySwarm.swarmCollision(b)) {
              toRemove5 += b
              playerScore += 10 //Should add score for killing an enemy
            }
          }
          bulletList --= toRemove5

          if (enemySwarm.swarm.size == 0) { //Reset swarm when all enemies are killed
            enemySwarm.addSwarm
          } 

          for (e <- enemySwarm.swarm) { //Swarm Movement
            e.swarmMovement
          }

        }

      }) 

      canvas.requestFocus()

      timer.start()

    }
  }

}






