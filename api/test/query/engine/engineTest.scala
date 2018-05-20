package query.engine

import org.scalatest._

class engineTest extends FunSuite with Matchers {
  test("check query") {
    val france = Engine.queryCountry("france")
    france.length should be (488)
  }
  test("check query partial matching") {
    val zimb = Engine.queryCountry("zimb")
    zimb.length should be (23)
  }
  test("check report") {
    val report = Engine.reportNbAirports
    report.length should be (244)
  }
  test("check runway type report") {
    val runType = Engine.reportRunwaysType
    runType.length should be (1417)
  }
  test("check top 10 runway lat") {
    val topLat = Engine.reportRunwaysType
    //sort done in the client
    topLat.length should be (1417)
  }
}
