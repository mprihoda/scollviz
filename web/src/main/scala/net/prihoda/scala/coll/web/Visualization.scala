package net.prihoda.scala.coll.web

import diode.react.{ ModelProxy, ReactConnectProxy }
import org.scalajs.dom._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object Visualization {
  protected def getInstance(): this.type = this

  val Root: ScalaComponent[ModelProxy[RootModel], Unit, Unit, CtorType.Props] =
    ScalaComponent
      .builder[ModelProxy[RootModel]]("Root")
      .render_P { proxy =>
        <.div(^.className := "container")(
          <.h1("Simple counter example"),
          <.p(
            <.a(
              ^.href := "https://github.com/suzaku-io/diode/tree/master/examples/simple"
            )("Source code")
          ),
          CounterView(
            proxy().counter,
            proxy.dispatchCB(Increase(2)),
            proxy.dispatchCB(Decrease(1)),
            proxy.dispatchCB(Reset)
          )
        )
      }
      .build

  def main(args: Array[String]): Unit = {
    val root = document.getElementById("root")
    val rootWrapper = AppCircuit.connect(m => m)
    rootWrapper(p => Root(p)).renderIntoDOM(root)
    // start the application by dispatching a Reset action
    AppCircuit(Reset)
  }
}
