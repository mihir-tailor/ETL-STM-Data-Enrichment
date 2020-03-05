package com.mcit.scala.ClassEntities

class MapRouteLookup(routes: List[Routes]){
  private val lookupTable: Map[Int, Routes] = routes.map(route => route.route_id -> route).toMap
  def lookup(routeId: Int): Routes = lookupTable.getOrElse(routeId,null)

}
