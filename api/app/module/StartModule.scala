package module

import com.google.inject.AbstractModule

class StartModule extends AbstractModule {
  override def configure() = {
    bind(classOf[CheckDb]).asEagerSingleton()
  }
}
