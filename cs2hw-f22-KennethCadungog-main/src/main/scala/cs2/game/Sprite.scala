package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

/** A graphical sprite object used for gaming or other visual displays
 *  
 *  @constructor create a new sprite based on the given image and initial location
 *  @param img the image used to display this sprite
 *  @param pos the initial position of the '''center''' of the sprite in 2D space
 */
abstract class Sprite (protected val img:Image,  var pos:Vec2, var width:Int, var height:Int) {

  /** moves the sprite a relative amount based on a specified vector
   *  
   *  @param direction - an offset that the position of the sprite should be moved by
   *  @return none/Unit
   */
  def move (direction:Vec2):Unit = {
    pos += direction
  }
  
  /** moves the sprite to a specific location specified by a vector (not a relative movement)
   *  
   *  @param location - the new location for the sprite's position
   *  @return none/Unit
   */
  def moveTo (location:Vec2):Unit = { 
     pos = location
  }
  
  /** Method to display the sprite at its current location in the specified Graphics2D context
   *  
   *  @param g - a GraphicsContext object capable of drawing the sprite
   *  @return none/Unit
   */

  def display (g:GraphicsContext):Unit = { 
    /*g.setFill(Color.Red)                                                            //Show hypothetical hitboxes. Probably doesn't work as intended
    g.fillRect(pos.x - width/2, pos.y - height/2, width, height) 
    g.rect( pos.x - width/2, pos.y - height/2, width, height)*/
    g.drawImage(img, pos.x,pos.y, width, height)
  }

  def collision(other: Sprite):Boolean = {

    var l1 = pos
    var r1 = pos + new Vec2(width, height)

    var l2 = other.pos
    var r2 = other.pos + new Vec2(other.width, other.height)

    l1.x < r2.x && l2.x < r1.x && l1.y < r2.y && l2.y < r1.y
    
  }
  
}

  


