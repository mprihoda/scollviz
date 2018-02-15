package net.prihoda.scala.coll.web

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.svg._

@react class ClassView extends StatelessComponent {
  case class Props(name: String)

  override def render(): ReactElement = {
    svg(width := "960", height := "960")(
      g(id := "ClassView")(
        g(id := "GenTraversableOnce", fill := "#D8D8D8")(
          rect(stroke := "#979797",
               strokeWidth := "1",
               width := "340",
               height := "40"),
          text(fontFamily := "Roboto",
               fontSize := "28",
               fontWeight := "normal",
               fill := "#000000")(tspan(x := "35", y := "28")(props.name))
        )
      )
    )
  }

}
