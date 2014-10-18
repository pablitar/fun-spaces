package ar.com.pablitar

case class Axis(index: Int)

object Axis {
  val X = Axis(0)
  val Y = Axis(1)
  val Z = Axis(2)
  val W = Axis(3)
  implicit def axisToInt(ax: Axis): Int = ax.index
}
