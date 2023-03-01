package cs2.adt

//class TheDequeue[A] extends Dequeue[A] {
class TheDequeue[A : Manifest] extends Dequeue[A] {
  private class Node(var data:A, var prev:Node, var next:Node)
  //private var head:Node = null
  //private var last:Node = null
  private var end:Node = new Node(Array.ofDim[A](1).apply(0), null, null)
  end.prev = end
  end.next = end
  private var len:Int = 0 

    def prepend(elem:A) = {                           // should add a single element to the logical "beginning" of the Deque
      if(len == 0){
        len += 1
        end.next = new Node(elem, end, end)
        end.prev = end.next 
        } else if (len > 0) {
          len += 1
          var store = end.next 
          end.next = new Node(elem, end, store) 
          store.prev = end.next  
        }
      }                       
    def append(elem:A) = {                            // should add a single element to the logical "end" of the Deque
      if(len == 0) {
        len += 1
        end.next = new Node(elem, end, end)
        end.prev = end.next 
        } else if (len > 0) {
          len += 1
          var store = end.prev 
          end.prev = new Node(elem, store, end) 
          store.next = end.prev 
        }
    }                    
    def front():A = {                                 // should return AND remove a single element from the logical "beginning" of the Deque
      if(len == 0) {
        throw new IndexOutOfBoundsException()
      }
      var storeData = end.next.data 
      end.next = end.next.next 
      storeData
    }                               
    def back():A = {                                  // should return AND remove a single element from the logical "end" of the Deque
      if(len == 0) {
        throw new IndexOutOfBoundsException()
      }
      var storeData = end.prev.data 
      end.prev = end.prev.prev 
      storeData
    }                                
    def peekFront():A = {                             // should return BUT NOT remove a single element from the logical "beginning"
      var data = end.next.data
      data
    }                       
    def peekBack():A = {                              // should return BUT NOT remove a single element from the logical "end"                
      var data = end.prev.data
      data 
    }                          
  def isEmpty():Boolean = { len == 0 }                // should return true if the Deque contains no elements, and false otherwise
}

