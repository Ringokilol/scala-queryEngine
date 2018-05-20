package module

import javax.inject._
import play.api.inject.ApplicationLifecycle
import query.engine._
import akka.actor.ActorSystem

// This creates an `ApplicationStart` object once at start-up and registers hook for shut-down.
@Singleton
class CheckDb @Inject() (actorSystem: ActorSystem, lifecycle: ApplicationLifecycle) {
  Engine.fillDb
}
