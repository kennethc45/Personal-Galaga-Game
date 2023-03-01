package cs2.adt

import org.junit._
import org.junit.Assert._


class QueueTester {
  var q:TheDequeue[Int] =  new TheDequeue[Int]()

  @Before def init():Unit = {
        q = new TheDequeue[Int]
    }

  @Test def checkIsEmpty():Unit = {
          assertTrue(q.isEmpty())
          q.append(12)
          assertTrue(!q.isEmpty())
  }

  @Test def checkAppendPrependandPeekFrontPeekBack():Unit = {
          q.prepend(9)
          q.prepend(11)
          q.prepend(20)
          q.prepend(17)
          q.append(5)
          q.append(50)
          q.append(1)

          assertTrue(q.peekFront() == 17)
          assertTrue(q.peekBack() == 1)
  }

  @Test def checkFrontandBack():Unit = {
          q.prepend(9)
          q.prepend(11)
          q.prepend(20)
          q.prepend(17)
          q.append(5)
          q.append(50)
          q.append(1)

          assertTrue(q.front() == 17)
          assertTrue(q.peekFront() == 20)
          assertTrue(q.back() == 1)
          assertTrue(q.peekBack() == 50)
   }

}
