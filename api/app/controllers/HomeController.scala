package controllers

import javax.inject._
import play.api.mvc._
import query.engine._
import play.api.libs.json._

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index = Action.async {
    Future {
      Ok(
        """How to use:
          |  /queryCountry/[name or code]     -> airports and runways of the country
          |  /reportNbAirports                -> top 10 and lowest 10 countries by number of airports
          |  /reportRunwaysType               -> count of runways type per country
          |  /reportCommonRunwaysLat          -> top 10 most common runway type""".stripMargin
      )
    }
  }

  def reportNbAirports = Action.async {
    Future {
      val countries = Engine.reportNbAirports
      Ok(Json.toJson(countries.reverse.take(10) ++ countries.take(10)))
    }
  }

  def reportRunwaysType = Action.async {
     Future {
       //Ok(Json.toJson(Engine.reportRunwaysType))
       val runwaysType = Engine.reportRunwaysType
       Ok(Json.toJson(runwaysType))
     }
  }

  def reportCommonRunwaysLat = Action.async {
    Future {
      val commonLat = Engine.reportCommonRunwaysLat
      Ok(Json.toJson(commonLat))
    }
  }


  def queryCountry(country: String) = Action.async {
    Future {
      val countryInfo = Engine.queryCountry(country.toLowerCase)
      Ok(Json.toJson(countryInfo))
    }
  }
}
