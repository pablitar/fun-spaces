package ar.com.pablitar

import ar.com.pablitar.point.Point2D

object SpaceTest extends App {
  println(Point2D(1, 2))

  val b = Point2D(1, 2)
  val a = Point2D(1, 2)

  val c = a + b
  
  println(c)
}