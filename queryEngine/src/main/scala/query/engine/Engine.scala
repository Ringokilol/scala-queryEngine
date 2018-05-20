package query.engine

import scala.concurrent.ExecutionContext.Implicits.{global => ec}
import query.DataBase._
import ctx._

import scala.concurrent.{ExecutionContext, Future}

object Engine {

  def dbCountriesCount = ctx.run(query[Countries].size)

  def dbAirportsCount = ctx.run(query[Airports].size)

  def dbRunwaysCount = ctx.run(query[Runways].size)

  def checkDbFiled: Boolean = {
    dbCountriesCount == 247 && dbAirportsCount == 46505 && dbRunwaysCount == 39536
  }


  def fillDb = {
    Future {
      if (!checkDbFiled) {
        ctx.run(query[Countries].delete)
        ctx.run(query[Airports].delete)
        ctx.run(query[Runways].delete)

        println("filling db with countries...")
        Parser.parseCount("countries.csv")
        println("filling db with airports...")
        Parser.parseAir("airports.csv")
        println("filling db with runways...")
        Parser.parseRun("runways.csv")
      }
      println("db full")
    }
  }


  def queryCountry(countryToSearch: String) = {
    val q = quote {
      query[Countries]
        .filter(e => ((lift(countryToSearch.length()) > 2) && e.name.toLowerCase().startsWith(lift(countryToSearch))) || e.code.toLowerCase() == lift(countryToSearch))
        .join(
          query[Runways].join(query[Airports]).on(_.airport_ref == _.id)
        ).on(_.code == _._2.iso_country)
        .map{
          case (country, (runways, airports)) =>
            (country.name, airports.iso_region, airports.municipality ,airports.name, runways.length_ft,
              runways.width_ft, runways.surface)
        }
    }
    ctx.run(q)
  }

  def reportNbAirports = {
    val q = quote {
      query[Airports]
        .join(query[Countries]).on((a, c) => a.iso_country == c.code)
        .groupBy(_._2.name)
        .map{case (iso, count) => (iso, count.size)}
        .sortBy{_._2}
    }
    ctx.run(q)
  }

  def reportRunwaysType = {
    val q2 = quote {
      query[Countries].join(
        query[Runways].join(query[Airports]).on(_.airport_ref == _.id)
      ).on(_.code == _._2.iso_country)
        .map{case (count, (run, air)) => (count, run, air)}
        .groupBy{case (country, run, air) => (country.name, run.surface)}
        .map{case ((name, surface), count) => (name, surface, count.size)}
        .sortBy(_._1)
    }
    ctx.run(q2)

  }

  def reportCommonRunwaysLat = {
    val q3 = quote {
      query[Runways]
        .groupBy(_.le_ident)
        .map{case (ident, count) => (ident, count.size)}
        .sortBy(- _._2)
        .take(10)
    }
    ctx.run(q3)
  }
}
