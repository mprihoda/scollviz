package net.prihoda.scala.coll.web

import japgolly.scalajs.react
import japgolly.scalajs.react.{ CtorType, Key }
import japgolly.scalajs.react.component.Scala.{ Component, Unmounted }
import japgolly.scalajs.react.vdom.svg_<^._

import scala.scalajs.js

object ClassView {
  case class Props(name: String)

  val component: Component[Props, Unit, Unit, CtorType.Props] =
    react.ScalaComponent
      .builder[Props]("ClassView")
      .render_P { props =>
        <.svg(^.width := 960, ^.height := 960)(
          <.g(^.id := "ClassView")(
            <.g(^.id := "GenTraversableOnce", ^.fill := "#D8D8D8")(
              <.rect(^.stroke := "#979797",
                     ^.strokeWidth := "1",
                     ^.width := "340",
                     ^.height := "40"),
              <.text(^.fontFamily := "Roboto",
                     ^.fontSize := "28",
                     ^.fontWeight := "normal",
                     ^.fill := "#000000")(
                <.tspan(^.x := "35", ^.y := "28")(props.name))
            )
          )
        )
      }
      .build

  def apply(name: String, key: js.UndefOr[Key] = js.undefined): Unmounted[Props, Unit, Unit] = {
    val props = Props(name)
    if (key.isDefined) {
      component.withKey(key.get)(props)
    } else {
      component(props)
    }
  }
}
