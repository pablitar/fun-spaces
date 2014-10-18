package ar.com.pablitar.funspaces

trait Positioned[T] {
  def space: AbstractSpace[T]

  var position: Point[T] = space.origin 
  
}

trait Speedy[T] <: Positioned[T]{
  
  def speed: Point[T]
  
  def applySpeed(delta:T) = position = position + speed * delta

}

class Car(var speed: Point[Double])(implicit val space:Space) extends Speedy[Double]