package ar.com.pablitar

import ar.com.pablitar.point.Point

case class Bounds[P<:Point[P]](val lowerBound:P, val upperBound: P) {
  def bound(p:P) = p.max(lowerBound).min(upperBound)
}
  
