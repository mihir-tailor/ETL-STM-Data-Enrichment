package com.mcit.scala.Main

import com.mcit.scala.ClassEntities._
import com.mcit.scala.RWdata.{DataReader, DataWriter}

object MainObj extends App{
  val readData : DataReader = new DataReader
  val tripList : List[Trips] = readData.getTripList
  tripList.map(x=> x.copy(note_en = toUpperCaseOptional(x.note_en))) foreach println
  def toUpperCaseOptional(in: Option[String]): Option[String] = in match {
    case None => None
    case Some(x2) => Some(x2.toUpperCase)
  }

  val routeList : List[Routes] = readData.getRouteList
  val calendarList : List[Calendar] = readData.getCalendarList
  val routeLookup = new MapRouteLookup(routeList)
  val CalendarLookup = new MapCalendarLookup(calendarList)
  val enrichedTripRoute : List[TripRoute] = tripList.map(trip => TripRoute(trip, routeLookup.lookup(trip.route_id)))
  val enrichedTrip : List[Enriched] = enrichedTripRoute.map(tripRoute => Enriched(tripRoute, CalendarLookup.lookup(tripRoute.trips.service_id)))
  val writer = new DataWriter(enrichedTrip)
  writer.writeData

}
