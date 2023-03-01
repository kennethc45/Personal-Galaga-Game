package cs2.units

class Volume (private var lit:Double = 0.0) {
  //Field - the volume stored in LITERS
  //private var lit:Double = 0.0

  //Basic math operators to add, subtract, and scale volumes
  def + (other:Volume):Volume = { new Volume(this.lit + other.lit) }
  def += (other:Volume):Unit  = { this.lit += other.lit }

  def - (other:Volume):Volume = { new Volume(this.lit - other.lit) }
  def -= (other:Volume):Unit  = { this.lit -= other.lit }

  def * (scalar:Double):Volume = { new Volume(this.lit * scalar) }
  def *= (scalar:Double):Unit  = { this.lit *= scalar }

  def / (scalar:Double):Volume = { new Volume(this.lit / scalar) }
  def /= (scalar:Double):Unit  = { this.lit /= scalar }

  //Getter functions that return in a variety of units
  def liters():Double = { lit }
  def milliliters():Double = { lit * 1000 }
  def gallons():Double = { lit * 0.264 }
  def quarts():Double = { lit * 1 }
  def pints():Double = { lit * 2.11 }
  def cups():Double = { lit * 4.23 }
  def teaspoons():Double = { lit * 202.88 }
  def tablespoons():Double = { lit * 67.63 }
}

object Volume {
  //"Constructor" apply methods operating in liters
  def apply():Volume = { new Volume() }
  def apply(amt:Double):Volume = { new Volume(amt) }

  //Alternative "static" methods to create volumes in other units
  def liters(amt:Double):Volume = { new Volume(amt) } //identical to an apply method
  def milliliters(amt:Double):Volume = { new Volume(amt / 1000) }
  def gallons(amt:Double):Volume = { new Volume(amt / 0.264) }
  def quarts(amt:Double):Volume = { new Volume(amt /1) }
  def pints(amt:Double):Volume = { new Volume(amt / 2.11) }
  def cups(amt:Double):Volume = { new Volume(amt /4.23) }
  def teaspoons(amt:Double):Volume = { new Volume(amt / 202.88) }
  def tablespoons(amt:Double):Volume = { new Volume(amt / 67.63) }
  
    def main(args: Array[String]): Unit = {
      val check = new Volume(1.0)
        println("The check is " + check.pints + " pints")
    }
}

