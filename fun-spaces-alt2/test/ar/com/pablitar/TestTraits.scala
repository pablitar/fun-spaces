package ar.com.pablitar

import ar.com.pablitar.point._

trait Positioned[P<:Point[P]] {
  
  def space: Space[P]

  var position: P = space.origin 
  
}

trait Speedy[P <: Point[P]] <: Positioned[P]{
  
  def bounds = space.infiniteBounds
  
  def speed: P
  
  def applySpeed(delta:Double) = position = bounds.bound(position + speed * delta)

}

trait Speedy3D extends Speedy[Point3D] {
  def space = Space._3D
} 


class Car(var speed: Point3D = Point3D(10, 10, 10)) extends Speedy3D {
  
}