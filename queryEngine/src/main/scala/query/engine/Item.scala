package query.engine

abstract class Item
case class Countries(id: Int,
                     code: String,
                     name: String,
                     continent: String,
                     wikipedia_link: Option[String],
                     keywords: Option[String]) extends Item
{}

case class Airports(id: Int,
                    ident: String,
                    `type`: String,
                    name: String,
                    latitude_deg: Float,
                    longitude_deg: Float,
                    elevation_ft: Option[Float],
                    continent: Option[String],
                    iso_country: String,
                    iso_region: String,
                    municipality: Option[String],
                    scheduled_service: Option[String],
                    gps_code: Option[String],
                    iata_code: Option[String],
                    local_code: Option[String],
                    home_link: Option[String],
                    wikipedia_link: Option[String],
                    keywords: Option[String]) extends Item
{}

case class Runways(id: Int,
                   airport_ref: Int,
                   airport_ident: Option[String],
                   length_ft: Option[Float],
                   width_ft: Option[Float],
                   surface: Option[String],
                   lighted: Option[Int],
                   closed: Option[Int],
                   le_ident: Option[String],
                   e_latitude_deg: Option[Float],
                   le_longitude_deg: Option[Float],
                   le_elevation_ft: Option[Float],
                   le_heading_degT: Option[Float],
                   le_displaced_threshold_ft: Option[Float],
                   he_ident: Option[String],
                   he_latitude_deg: Option[Float],
                   he_longitude_deg: Option[Float],
                   he_elevation_ft: Option[Float],
                   he_heading_degT: Option[Float],
                   he_displaced_threshold_ft: Option[Float])
{}
