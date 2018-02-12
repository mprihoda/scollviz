package net.prihoda.scala.coll.web

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.vdom.html_<^._

object CounterView {
  case class Props(
      counter: Int,
      onIncrease: Callback,
      onDecrease: Callback,
      onReset: Callback
  )

  val component: ScalaComponent[Props, Unit, Unit, CtorType.Props] =
    ScalaComponent
      .builder[Props]("CounterView")
      .render_P { props =>
        <.div(
          <.h3("Counter"),
          <.p("Value = ", <.b(props.counter)),
          <.div(
            ^.className := "btn-group",
            <.button(^.className := "btn btn-default",
                     ^.onClick --> props.onIncrease,
                     "Increase"),
            <.button(^.className := "btn btn-default",
                     ^.onClick --> props.onDecrease,
                     "Decrease"),
            <.button(^.className := "btn btn-default",
                     ^.onClick --> props.onReset,
                     "Reset")
          )
        )
      }
      .build

  def apply(
      counter: Int,
      onIncrease: Callback,
      onDecrease: Callback,
      onReset: Callback
  ): Unmounted[Props, Unit, Unit] =
    component(Props(counter, onIncrease, onDecrease, onReset))
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
