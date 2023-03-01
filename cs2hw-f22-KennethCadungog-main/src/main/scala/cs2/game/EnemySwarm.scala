package cs2.game

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import cs2.util.Vec2
import scala.collection.mutable.Buffer

/** contains the control and logic to present a coordinated set of Enemy objects.
 *  For now, this class generates a "grid" of enemy objects centered near the
 *  top of the screen.
 *
 *  @param nRows - number of rows of enemy objects
 *  @param nCols - number of columns of enemy objects
 */
class EnemySwarm(private val nRows:Int, private val nCols:Int, private val canvasWidth:Int, private val canvasHeight:Int) {

  /** method to display all Enemy objects contained within this EnemySwarm
   *
   *  @param g - the GraphicsContext to draw into
   *  @return none/Unit
   */
   

   val enemyPath2 = getClass().getResource("/images/Gabe.png")
   val enemyImg = new Image(enemyPath2.toString)
   val bulletPath2 = getClass().getResource("/images/bullet.png")
   val bulletImg = new Image(bulletPath2.toString)
   var bullets:List[Bullet] = List()

  var swarm:Buffer[Enemy] = Buffer ()

  def addSwarm() = {
    for (c <- 1 to nCols) {

      for (r <- 1 to nRows) {
        swarm += new Enemy(enemyImg, new Vec2(100 + (400/nCols)*c, (400/nRows)*r), bulletImg, 200, 250)
      }
    }
  }

  def display(g:GraphicsContext):Unit = {
    for (e <- swarm) {
      e.display(g)
    }
  }


  def swarmCollision(a:Sprite):Boolean = { //If player's bullet hits enemy, then enemy dies
    var check = false
    var toRemove:Buffer[Enemy] = Buffer()
    for (e <- swarm) { 
          if(e.collision(a)){  
            toRemove += e
            check = true
          }       
    }
    swarm --= toRemove 
    check

  }

  def removeSwarm() = {
    var toRemove2:Buffer[Enemy] = Buffer()
    for (e <- swarm) {
      toRemove2 += e
    }
    swarm --= toRemove2
  }
  
  

  /** overridden method of ShootsBullets. Creates a single, new bullet instance
   *  originating from a random enemy in the swarm. (Not a bullet from every
   *  object, just a single from a random enemy)
   *
   *  @return Bullet - the newly created Bullet object fired from the swarm
   */
  def shoot():Bullet = {

    val random = scala.util.Random
    val rSwarm = swarm(random.nextInt(swarm.length))
    rSwarm.shoot()
  }

  override def clone():EnemySwarm = {
    var ret = new EnemySwarm(nRows, nCols, canvasWidth, canvasHeight)
    //ret.nRows = nRows.clone()

    for (x <- swarm) {
      ret.swarm += x.clone()
    }
    ret
  }

}
