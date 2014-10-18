package ar.com.pablitar

import org.junit.Test
import org.junit.Assert._
import ar.com.pablitar.point._

import ar.com.pablitar.Axis._

class PointTests {

  def a = Point2D(0, 1)
  def b = Point2D(2, 1)
  def c = Point3D(2, 1, 3)
  def d = Point3D(3, 1, 2)
  
  @Test
  def shouldBeAbleToGetComponents() {
    assertEquals(1, a(Y), 0.0001)
  }

  @Test
  def shouldBeAbleToSum2D() {
    assertEquals(Point2D(2, 2), a + b)
  }

  @Test
  def shouldBeAbleToSum3D() {
    assertEquals(Point3D(5, 2, 5), c + d)
  }

  @Test
  def shouldBeAbleToDefineGenericFunctionForPoints() {
    def sumAll[X <: Point[X]](points: X*) = points.reduce(_ + _)

    assertEquals(Point2D(2, 2), sumAll(a, b))
    assertEquals(Point3D(5, 2, 5), sumAll(c, d))
  }

  @Test
  def shouldBeAbleToSumOnScalar() {
    assertEquals(Point2D(6, 3), b * 3)
    assertEquals(Point3D(1, 0.5, 1.5), c * 0.5)
  }

  @Test
  def shouldBeAbleToDoOperationsOnAxis() {
    assertEquals(Point2D(3, 1), a.sumOn(X)(3))
  }

}