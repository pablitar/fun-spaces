package ar.com.pablitar.funspaces

import org.junit.Test
import org.junit.Assert._



class TraitsTest {
  
  @Test
  def aCarShouldChangePositionWithSpeed() {
    import Spaces.Space3D._
    val aCar = new Car(point(10,10,10))
    aCar.applySpeed(0.1)
    assertEquals(point(1,1,1),aCar.position) 
  }
  
  @Test
  def aCarShouldChangePositionWithSpeedIn2D() {
    import Spaces.Space2D._
    val aPoint = point(10,10)
    val aCar = new Car(aPoint)
    aCar.applySpeed(0.1)
    Spaces.Space3D.point(10,23,2) + aPoint
    assertEquals(point(1,1),aCar.position) 
  }

}