package ar.com.pablitar.funspaces

case class Component[T](axis: Axis, value: T)(implicit sp: AbstractSpace[T]) {
  def +(component: Component[T]) = Component(axis, sp.plusElement(this.value, component.value))

  def *(scalar: T) = Component(axis, sp.elementByScalar(this.value, scalar))

}

trait Axis {
  def apply[T](value: T)(implicit sp: AbstractSpace[T]) = Component[T](this, value)
}

object Axis {
  case object X extends Axis
  case object Y extends Axis
  case object Z extends Axis
}

//Esto todavía no hace nada. Es una idea
trait Direction {
  def axis: Axis
}

object Direction {
  case object Left extends Direction { val axis = Axis.X }
  case object Right extends Direction { val axis = Axis.X }
  case object Up extends Direction { val axis = Axis.Y }
  case object Down extends Direction { val axis = Axis.Y }
  case object Front extends Direction { val axis = Axis.Z }
  case object Back extends Direction { val axis = Axis.Z }

}

case class Point[T](components: Component[T]*)(implicit sp: AbstractSpace[T]) {
  val componentMap = Map(components.map((x) => (x.axis, x)): _*).withDefault { x => sp.defaultFor(x) }

  def +(x: Point[T]) = sp.plus(this, x)

  def *(s: T) = sp.scalarProduct(this, s)

  def sumOn(ax: Axis)(v: T) = sp.pointFromComponents(components.map { x => if (x.axis.equals(ax)) Component(ax, v) else x }: _*)

  def apply(ax: Axis) = componentMap(ax).value
}

trait AbstractSpace[T] {

  implicit def currentSpace = this;

  def axisList: Seq[Axis]

  def zeroElement: T

  def plusElement(x: T, y: T): T

  def elementByScalar(x: T, y: T): T

  def pointFromComponents(components: Component[T]*) = Point(components: _*)

  def point(values: T*) = Point(axisList.zip(values).map((x) => Component[T](x._1, x._2)): _*)

  def origin: Point[T] = Point(axisList.map { x => Component[T](x, zeroElement) }: _*)

  def plus(x: Point[T], y: Point[T]) = Point(x.components.zip(y.components).map((x) => x._1 + x._2): _*)

  def scalarProduct(x: Point[T], s: T) = Point(x.components.map(_ * s): _*)

  def defaultFor(ax: Axis) = Component(ax, zeroElement) //Por defecto

}

//Debería ser posible definir un espacio genérico para tipos numéricos...
//SPAAAAACEEEEEE!
case class Space(val axisList: Axis*) extends AbstractSpace[Double] {
  def zeroElement = 0
  def plusElement(x: Double, y: Double) = x + y
  def elementByScalar(x: Double, y: Double) = x * y
}

trait SpaceContext[T] {
  def space: AbstractSpace[T]
  def point(values: T*) = space.point(values: _*)
}

object Spaces {
  object Space2D extends SpaceContext[Double] {
    implicit def space = Space(Axis.X, Axis.Y)
  }

  object Space3D extends SpaceContext[Double] {
    implicit def space = Space(Axis.X, Axis.Y, Axis.Z)
  }
}

