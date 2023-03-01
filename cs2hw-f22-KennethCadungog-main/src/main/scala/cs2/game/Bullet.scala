package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scala.collection.mutable.Buffer

/** Representation of a bullet/projectile for a simple game based on sprites.
 *  Handles all information regarding a bullet's position, movements, and 
 *  abilities.
 *  
 *  @param pic the image representing the bullet
 *  @param initPos the initial position of the '''center''' of the bullet
 *  @param vel the initial velocity of the bullet
 */
class Bullet(pic:Image, initPos:Vec2, private var vel:Vec2, width:Int, height:Int) extends Sprite(pic, initPos, width, height) {

  /** advances the position of the Bullet over a single time step
   * 
   *  @return none/Unit
   */
  def timeStep():Unit = {
    initPos += vel
  }

  def isDead():Boolean = { //Defines when a bullet is considered dead
    if(pos.x < 0 || pos.x > 1000 || pos.y < 0 || pos.y > 1000) true
    else false
  }

  override def clone():Bullet = {
    new Bullet(pic, pos.clone(), vel, width, height)
  }
  
}
