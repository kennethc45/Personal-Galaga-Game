package cs2.adt

class DEPQ[A <% Ordered[A]] extends DoubleEndPriorityQueue[A] {     //DEPQ goes from biggest to smallest
    //private class Node(var data:A, var next:Node)
    private class Node(var data:A, var prev:Node, var next:Node)
    private var head:Node = null
    private var end:Node = null 
    private var len:Int = 0 

    def isEmpty():Boolean = { head == null }                        //Return true if there are no elements in the DEPQ

    def add(elem:A):Unit = {                                        //Add an element to the DEPQ
        len += 1
        if(head == null || elem > head.data) {
          head = new Node(elem, null, head)
          if(len == 1) {
            end = head 
          } else {
            head.next.prev = head 
          }
        } else {
          var rover = head
          while(rover.next != null && rover.next.data > elem) {
            rover = rover.next
          }
          rover.next = new Node(elem, rover, rover.next)
          if(rover.next.next == null) {
            end = rover.next 
          }
        }
    }     

    def peekMax():A  = { head.data }                                //Return the largest element in the DEPQ
    def max():A = {                                                 //Return AND Remove the largest element from the DEPQ
        val ret = head.data
        head = head.next
        ret
    }	                   
  def peekMin():A = { end.data }                                    //Return the smallest element in the DEPQ	                                
    def min():A = {                                                 //Return AND Remove the smallest element from the DEPQ
        len -= 1
        var ret = end.data
        end = end.prev 
        end.next = null 
        ret
    }                                    
}

object DEPQ {                                                       //Used for immediate testing
  def main(args:Array[String]):Unit = {
    val one = new DEPQ[Int]()
    //one.add(20)
    one.add(15)
    one.add(7)
    one.add(45)
    one.add(100)
    one.add(55)
    one.add(2)

    println("head " + one.head.data)
    println("peekMax " + one.peekMax())
    println("return " + one.max())
    println("new head " + one.head.data)

    println("end " + one.end.data)
    println("peekMin " + one.peekMin())
    println("return " + one.min())
    println("new end " + one.end.data)

    var rover = one.head
    for(x <- 0 until one.len) {
      println(rover.data)
      rover = rover.next 
    }
  }
}