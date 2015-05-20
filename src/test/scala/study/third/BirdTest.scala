package study.third

import org.junit.Test

class BirdTest {
  @Test def fly() {
    val birds = List(new Pigeon, new Hawk, new Frigatebird)
    birds.foreach(bird => bird.fly())
  }
  
  @Test def swim() {
    val birds = List(new Pigeon, new Hawk, new Penguin)
    birds.foreach(bird => bird.swim())
  }
}