package cs2.adt

import org.junit._
import org.junit.Assert._

class DEPQTester {
    var q:DEPQ[Int] =  new DEPQ[Int]

    @Before def init():Unit = {
        q = new DEPQ[Int]
    }

    @Test def checkIsEmpty():Unit = {
            assertTrue(q.isEmpty())
            q.add(12)
            assertTrue(!q.isEmpty())
    }

    @Test def checkPeekMaxandMax():Unit = {
            q.add(15)
            q.add(7)
            q.add(45)
            q.add(100)
            q.add(55)
            q.add(2)

            assertTrue(q.peekMax() == 100)
            assertTrue(q.max() == 100)
            assertTrue(q.peekMax() == 55)
    }

    @Test def checkPeekMinandMin():Unit = {
            q.add(15)
            q.add(7)
            q.add(45)
            q.add(100)
            q.add(55)
            q.add(2)

            assertTrue(q.peekMin() == 2)
            assertTrue(q.min() == 2)
            assertTrue(q.peekMin() == 7)
    }


}







