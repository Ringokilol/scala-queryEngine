package controllers

import scala.concurrent.Future

import org.scalatestplus.play._

import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import scala.concurrent.ExecutionContext.Implicits.global

class ApiTest extends PlaySpec with Results {
  "Example Page#index" should {
    "should be valid" in {
      val controller = new HomeController(stubControllerComponents())
      val resApiIndex: Future[Result] = controller.index().apply(FakeRequest())
      status(resApiIndex) must be (200)
    }
  }

  "Test reportNbAirports" should {
    "should be valid" in {
      val controller = new HomeController(stubControllerComponents())
      val resApiIndex: Future[Result] = controller.reportNbAirports.apply(FakeRequest())
      status(resApiIndex) must be (200)
    }
  }

  "Test reportRunwaysType" should {
    "should be valid" in {
      val controller = new HomeController(stubControllerComponents())
      val resApiIndex: Future[Result] = controller.reportRunwaysType.apply(FakeRequest())
      status(resApiIndex) must be (200)
    }
  }

  "Test reportCommonRunwaysLat" should {
    "should be valid" in {
      val controller = new HomeController(stubControllerComponents())
      val resApiIndex: Future[Result] = controller.reportCommonRunwaysLat.apply(FakeRequest())
      status(resApiIndex) must be (200)
    }
  }

  "Test query with maj" should {
    "should be valid" in {
      val controller = new HomeController(stubControllerComponents())
      val resApiIndex: Future[Result] = controller.queryCountry("France").apply(FakeRequest())
      status(resApiIndex) must be (200)
    }
  }

  "Test query partial matching" should {
    "should be valid" in {
      val controller = new HomeController(stubControllerComponents())
      val resApiIndex: Future[Result] = controller.queryCountry("zimb").apply(FakeRequest())
      status(resApiIndex) must be (200)
    }
  }
}
