package com.mcit.scala.RWdata
import java.io.{File, FileWriter}

import au.com.bytecode.opencsv._
import com.mcit.scala.ClassEntities.Enriched

class DataWriter(enrichedList: List[Enriched]) {
  val outputPath = "/home/bd-user/IdeaProjects/DataEnrichment/output_mihir.csv"
  var file: File = new File(outputPath)
  val output: FileWriter = new FileWriter(file)
  val writer: CSVWriter = new CSVWriter(output)
  val csvSchema = Array("Route_ID", "Service_ID", "Trip_ID", "Trip_Head_Sign", "Direction_ID", "Shape_ID", "Wheelchair_Accessible", "Note_FR", "Note_En", "Agency_ID", "Route_Short_Name", "Route_Long_Name", "Route_Type", "Route_URL", "Route_Colour", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Start Date", "End Date")

  def writeData: Unit = {
    writer.writeNext(csvSchema.mkString(" "))
    enrichedList.foreach(element => {
      val data = Array(element.tripRoute.routes.route_id.toString, element.calendar.service_id.toString,
        element.tripRoute.trips.trip_id.toString, element.tripRoute.trips.trip_headsign.toString,
        element.tripRoute.trips.direction_id.toString, element.tripRoute.trips.shape_id.toString,
        element.tripRoute.trips.wheelchair_accessible.toString, element.tripRoute.trips.note_fr.toString,
        element.tripRoute.trips.note_en.toString, element.tripRoute.routes.agency_id.toString,
        element.tripRoute.routes.route_short_name.toString, element.tripRoute.routes.route_long_name.toString,
        element.tripRoute.routes.route_type.toString, element.tripRoute.routes.route_url.toString,
        element.tripRoute.routes.route_color.toString, element.calendar.monday.toString,
        element.calendar.tuesday.toString, element.calendar.wednesday.toString,
        element.calendar.thursday.toString, element.calendar.friday.toString,
        element.calendar.saturday.toString, element.calendar.sunday.toString,
        element.calendar.start_date.toString, element.calendar.end_date.toString)
    writer.writeNext(data.mkString(" "))
    })
    writer.close();
  }
}
