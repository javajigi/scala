package fp

import java.util.function.BooleanSupplier
import java.util.stream.Collectors

import com.google.common.base.Strings

import scala.util.Random

class Racing {
  def run(carPositions: List[Int], time: Int) = {
    forwardValues(randomValues(carPositions.length, time)).foldLeft(carPositions)((positions, x) => {
      val movedPositions = (positions zip x).map(t => t._1 + t._2)
      draw(drawLines(movedPositions))
      movedPositions
    })
  }
  
  def randomValues(countOfCar: Int, time: Int) = {
    (0 until time).toList
      .map(i => randomValue(countOfCar))
  }

  private def randomValue(countOfCar: Int) = {
    (0 until countOfCar).toList.map(i => Random.nextInt(10))
  }

  def forwardValues(randomValues: List[List[Int]]) = {
    randomValues.map(
      randomValue => randomValue.map(r => {
        if (r > 4) 1
        else 0
      })
    )
  }

  private def draw(drawLines: List[String]) {
    drawLines.foreach(println)
    println("")
  }

  private def drawLines(carPositions: List[Int]) = {
    carPositions.map(p => Strings.repeat("-", p))
  }
}

