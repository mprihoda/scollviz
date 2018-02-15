package net.prihoda.scala.coll.web

import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.{Component, Unmounted}
import japgolly.scalajs.react.vdom.html_<^._
import slinky.scalajsreact.Converters._

object ClassDiagramRoot {

  class Backend($ : BackendScope[ModelProxy[ClassDiagram], Unit]) {
    def renderClassView(c: ClassDescriptor): TagMod = {
      ClassView(name = c.name)
    }

    def render(proxy: ModelProxy[ClassDiagram]): VdomElement = {
      <.div(^.className := "container")(
        <.h1("Scala collection library visualization")
      )(
        proxy().classes.map(renderClassView): _*
      )
    }
  }

  val component
    : Component[ModelProxy[ClassDiagram], Unit, Backend, CtorType.Props] =
    ScalaComponent
      .builder[ModelProxy[ClassDiagram]]("ClassDiagramRoot")
      .renderBackend[Backend]
      .build

  def apply(proxy: ModelProxy[ClassDiagram])
    : Unmounted[ModelProxy[ClassDiagram], Unit, Backend] = component(proxy)
}
