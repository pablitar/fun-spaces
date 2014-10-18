package ar.com.pablitar

import ar.com.pablitar.point._

class Space[P<:Point[P]](val pf: PointFactory[P], val axisList:Axis *){
  def origin = pf.zero
  
  def negativeInfinity = pf.negativeInfinity
  def positiveInfinity = pf.positiveInfinity
  
  def infiniteBounds = Bounds(pf.negativeInfinity, pf.positiveInfinity)
}

object Space {
  def _2D = new Space(Point2D, Axis.X, Axis.Y)
  def _3D = new Space(Point3D, Axis.X, Axis.Y, Axis.Z)
}