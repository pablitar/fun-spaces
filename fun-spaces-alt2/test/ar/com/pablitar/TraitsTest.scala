package ar.com.pablitar

import org.junit.Test
import org.junit.Assert._
import ar.com.pablitar.point._

class TraitsTest {
  
  @Test
  def aCarShouldChangePositionWithSpeed() {
    val aCar = new Car
    aCar.applySpeed(0.1)
    
    assertEquals(Point3D(1,1,1),aCar.position) 
  }

}