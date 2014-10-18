package ar.com.pablitar

package point {
  trait PointFactory[T <: Point[T]] {
    private[point] def create(components: Seq[Double]): T

    def space: Space[T]
    
    def fill(x:Double) = create(Seq.fill(space.axisList.length)(x)) 

    def zero: T = fill(0)

    def negativeInfinity: T = fill(Double.NegativeInfinity)
    def positiveInfinity: T = fill(Double.PositiveInfinity)
  }

  trait Point[T <: Point[T]] {
    def components: Seq[Double]
    def companion: PointFactory[T]

    def apply(ax: Axis) = components(ax)

    def mergeUsing(x: T, operation: ((Double, Double)) => Double) = companion.create(components.zip(x.components).map(operation))

    def +(x: T) = mergeUsing(x, (y => y._1 + y._2))
    def sumOn(ax: Axis)(v: Double) = companion.create(components.patch(ax, Seq(v), 1))

    def *(x: Double) = companion.create(components.map(_ * x))

    def min(x: T) = mergeUsing(x, x => Math.min(x._1, x._2))
    def max(x: T) = mergeUsing(x, x => Math.max(x._1, x._2))

    override def toString() = s"(${components.mkString(", ")})"
  }

  object Point2D extends PointFactory[Point2D] {
    override private[point] def create(components: Seq[Double]) = new Point2D(components)

    def apply(x: Double, y: Double) = new Point2D(Vector(x, y))
    
    def space = Space._2D
  }

  case class Point2D private[point] (someComponents: Seq[Double]) extends Point[Point2D] {
    val components = someComponents
    val companion = Point2D
  }

  object Point3D extends PointFactory[Point3D] {
    override private[point] def create(components: Seq[Double]) = new Point3D(components)

    def apply(x: Double, y: Double, z: Double) = new Point3D(Vector(x, y, z))
    
    def space = Space._3D
  }

  case class Point3D private[point] (someComponents: Seq[Double]) extends Point[Point3D] {
    val components = someComponents
    val companion = Point3D
  }
}


