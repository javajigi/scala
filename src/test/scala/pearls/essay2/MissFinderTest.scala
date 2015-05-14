package pearls.essay2

import org.junit.Test
import org.junit.Assert._
import java.io.PrintWriter
import java.io.File

import pearls.support.Utils._

class MissFinderTest {
  @Test def getCenter() {
    assertEquals(5, MissFinder.getCenter(0, 10))
    assertEquals(2, MissFinder.getCenter(0, 5))
    assertEquals(4, MissFinder.getCenter(3, 5))
  }

  @Test def getValueWhenHasValue() {
    val fileName = "resources/pearls/essay2/temp.txt"
    val value = 5;
    withPrintWriter(new File(fileName)) {
      pw => pw.println(value)
    }
    assertEquals(value, MissFinder.getValue(fileName))
  }

  @Test def getValueWhenNoValue() {
    val fileName = "resources/pearls/essay2/temp.txt"
    withPrintWriter(new File(fileName)) {
      pw => pw.println("")
    }
    assertEquals(0, MissFinder.getValue(fileName))
  }

  @Test def getMissedValue() {
    assertEquals(4, MissFinder.getMissedValue(0, 5))
    assertEquals(6, MissFinder.getMissedValue(5, 0))
  }
}