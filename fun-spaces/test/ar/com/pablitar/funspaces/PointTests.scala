package ar.com.pablitar.funspaces

import org.junit.Test
import org.junit.Assert._

import ar.com.pablitar.funspaces.Axis._;

import java.awt.geom.Point2D

class PointTests {
  def space2D = Space(X, Y)
  def space3D = Space(X, Y, Z)
  

  def a = space2D.point(0, 1)
  def b = space2D.point(2, 1)
  def c = space3D.point(2, 1, 3)
  def d = space3D.point(3, 1, 2)
  
  @Test
  def shouldBeAbleToGetComponents() {
    assertEquals(1.0, a(Y), 0.0001)
  }

  @Test
  def shouldBeAbleToSum2D() {
    assertEquals(space2D.point(2, 2), a + b)
  }

  @Test
  def shouldBeAbleToSum3D() {
    assertEquals(space3D.point(5, 2, 5), c + d)
  }

  @Test
  def shouldBeAbleToDefineGenericFunctionForPoints() {
    def sumAll[T](points: Point[T]*) = points.reduce(_ + _)

    assertEquals(space2D.point(2, 2), sumAll(a, b))
    assertEquals(space3D.point(5, 2, 5), sumAll(c, d))
  }

  @Test
  def shouldBeAbleToSumOnScalar() {
    assertEquals(space2D.point(6, 3), b * 3)
    assertEquals(space3D.point(1, 0.5, 1.5), c * 0.5)
  }

  @Test
  def shouldBeAbleToDoOperationsOnAxis() {
    assertEquals(space2D.point(3, 1), a.sumOn(X)(3))
  }

}