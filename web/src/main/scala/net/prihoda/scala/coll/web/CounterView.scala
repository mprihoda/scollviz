package net.prihoda.scala.coll.web

import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object CounterView {
  val component
    : ScalaComponent[ModelProxy[RootModel], Unit, Unit, CtorType.Props] =
    ScalaComponent
      .builder[ModelProxy[RootModel]]("CounterView")
      .render_P { proxy =>
        <.div(
          <.h3("Counter"),
          <.p("Value = ", <.b(proxy().counter)),
          <.div(
            ^.className := "btn-group",
            <.button(^.className := "btn btn-default",
                     ^.onClick --> proxy.dispatchCB(Increase(2)),
                     "Increase"),
            <.button(^.className := "btn btn-default",
                     ^.onClick --> proxy.dispatchCB(Decrease(1)),
                     "Decrease"),
            <.button(^.className := "btn btn-default",
                     ^.onClick --> proxy.dispatchCB(Reset),
                     "Reset")
          )
        )
      }
      .build
}

/**
  * Counter view renders the counter value and provides interaction through
  * various buttons affecting the counter value.
  *
  * @param counter  Model reader for the counter value
  * @param dispatch Dispatcher
class CounterView(counter: ModelRO[Int], dispatch: Dispatcher) {
  def render = {
    div(
      h3("Counter"),
      p("Value = ", b(counter())),
      div(
        cls := "btn-group",
        button(cls := "btn btn-default", onclick := (() => dispatch(Increase(2))), "Increase"),
        button(cls := "btn btn-default", onclick := (() => dispatch(Decrease(1))), "Decrease"),
        button(cls := "btn btn-default", onclick := (() => dispatch(Reset)), "Reset")
      )
    )
  }
}
  */
