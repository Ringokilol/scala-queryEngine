package query.engine

import scala.io.Source
import query.DataBase._
import ctx._

object Parser {

  def parseCount(filename: String): Unit = {
    val buf = Source.fromResource(filename)
    buf.getLines().drop(1)
    val item = buf.getLines().flatMap {
      e => listToCount(parseLine(e.mkString + '\n'))
    }
    val lq = quote {
      liftQuery(item.toList).foreach (e => query[Countries].insert(e))
    }
    ctx.run(lq)
    buf.close()
  }

  def parseAir(filename: String): Unit = {
    val buf = Source.fromResource(filename)
    buf.getLines().drop(1)
    val item = buf.getLines().flatMap {
      e => listToAir(parseLine(e.mkString + '\n'))
    }
    val lq = quote {
      liftQuery(item.toList).foreach (e => query[Airports].insert(e))
    }
    ctx.run(lq)
    buf.close()
  }

  def parseRun(filename: String): Unit = {
    val buf = Source.fromResource(filename)
    buf.getLines().drop(1)
    val item = buf.getLines().flatMap {
      e => listToRun(parseLine(e.mkString + '\n'))
    }
    val lq = quote {
      liftQuery(item.toList).foreach (e => query[Runways].insert(e))
    }
    ctx.run(lq)
    buf.close()
  }



  def parseLine(line: String): List[Option[String]] = line.foldLeft(("", List[String](), true)) {
    case (acc, e) if e == '\"' => (acc._1 + e, acc._2, !acc._3)
    case (acc, e) if (e == ',' && acc._3) || e == '\n' =>
      ("", acc._1.trim.stripPrefix("\"").stripSuffix("\"")::acc._2, acc._3)
    case (acc, e) => (acc._1 + e, acc._2, acc._3)
  }._2.reverse.map {
    case "" => None
    case str => Some(str)
  }


  def listToCount(li: List[Option[String]]): Option[Countries] = li match {
    case List(Some(id), Some(code), Some(name), Some(conti), wiki, keyword) =>
      Some(Countries(id.toInt, code, name, conti, wiki, keyword))
    case _ => None
  }
  def listToAir(li: List[Option[String]]): Option[Airports] = li match {
     case List(Some(id),
               Some(ident),
               Some(ty),
               Some(name),
               Some(latitude_deg),
               Some(longitude_deg),
               elevation_ft,
               continent,
               Some(iso_country),
               Some(iso_region),
               municipality,
               scheduled_service,
               gps_code,
               iata_code,
               local_code,
               home_link,
               wikipedia_link,
               keywords) => Some(Airports(id.toInt,
                                          ident,
                                          ty,
                                          name,
                                          latitude_deg.toFloat,
                                          longitude_deg.toFloat,
                                          my_float(elevation_ft),
                                          continent,
                                          iso_country,
                                          iso_region,
                                          municipality,
                                          scheduled_service,
                                          gps_code,
                                          iata_code,
                                          local_code,
                                          home_link,
                                          wikipedia_link,
                                          keywords))
    case _ => None
  }
  def listToRun(li: List[Option[String]]): Option[Runways] = li match {
    case List(Some(id),
              Some(airport_ref),
              airport_ident,
              length_ft,
              width_ft,
              surface,
              lighted,
              closed,
              le_ident,
              e_latitude_deg,
              le_longitude_deg,
              le_elevation_ft,
              le_heading_degT,
              le_displaced_threshold_ft,
              he_ident,
              he_latitude_deg,
              he_longitude_deg,
              he_elevation_ft,
              he_heading_degT,
              he_displaced_threshold_ft) => Some(Runways(id.toInt,
                                                         airport_ref.toInt,
                                                         airport_ident,
                                                         my_float(length_ft),
                                                         my_float(width_ft),
                                                         surface,
                                                         my_int(lighted),
                                                         my_int(closed),
                                                         le_ident,
                                                         my_float(e_latitude_deg),
                                                         my_float(le_longitude_deg),
                                                         my_float(le_elevation_ft),
                                                         my_float(le_heading_degT),
                                                         my_float(le_displaced_threshold_ft),
                                                         he_ident,
                                                         my_float(he_latitude_deg),
                                                         my_float(he_longitude_deg),
                                                         my_float(he_elevation_ft),
                                                         my_float(he_heading_degT),
                                                         my_float(he_displaced_threshold_ft)
    ))
    case _ => None
  }
  def my_float(s: Option[String]): Option[Float] = s match {
    case None => None
    case Some(f) => Some(f.toFloat)
  }
  def my_int(s: Option[String]): Option[Int] = s match {
    case None => None
    case Some(f) => Some(f.toInt)
  }
}
