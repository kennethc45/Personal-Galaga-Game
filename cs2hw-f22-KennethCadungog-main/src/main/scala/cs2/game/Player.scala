package cs2.game

import scalafx.scene.image.Image
import scalafx.scene.canvas.Canvas
import cs2.util.Vec2
import scalafx.scene.input.KeyCode

/** The player representation for a simple game based on sprites. Handles all
 *  information regarding the player's positions, movements, and abilities.
 *
 *  @param avatar the image representing the player
 *  @param initPos the initial position of the '''center''' of the player
 *  @param bulletPic the image of the bullets fired by this player
 */
class Player(avatar:Image, initPos:Vec2, private val bulletPic:Image, width:Int, height:Int) extends Sprite(avatar, initPos, width, height) {

  def cap(c:Double):Double = {
        if(c < 0) 0
        else if(c > 800) 800 //1000
        else c
      }

  /** moves the player sprite one "step" to the left.  The amount of this
   *  movement will likely need to be tweaked in order for the movement to feel
   *  natural.
   *
   *  @return none/Unit
   */
  def moveLeft():Unit = {
        pos.x -= 5
        pos.x = cap(pos.x)
      }
  
  /** moves the player sprite one "step" to the right (see note above)
   *
   *  @return none/Unit
   */
  def moveRight():Unit = { 
        pos.x += 5
        pos.x = cap(pos.x)
  }

  def moveUp():Unit = { 
        pos.y -= 5
        pos.y = cap(pos.y)
  }

  def moveDown():Unit = { 
        pos.y += 5
        pos.y = cap(pos.y)
  }

  /** creates a new Bullet instance beginning from the player, with an
   *  appropriate velocity
   *
   *  @return Bullet - the newly created Bullet object that was fired
   */
  def shoot():Bullet = {
     new Bullet (bulletPic, pos.clone(), new Vec2 (0, -0.5), 70, 100)
  }

  override def clone():Player = {
    new Player(avatar, pos.clone(), bulletPic, width, height)
  }

}
