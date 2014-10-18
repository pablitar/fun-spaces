package ar.com.pablitar.funspaces

import org.junit.Test
import org.junit.Assert._

import Spaces.Space3D._

class TraitsTest {
  
  @Test
  def aCarShouldChangePositionWithSpeed() {
    val aCar = new Car(point(10,10,10))
    aCar.applySpeed(0.1)
    assertEquals(point(1,1,1),aCar.position) 
  }

}