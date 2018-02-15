package net.prihoda.scala.coll.web

import org.scalajs.dom
import slinky.hot

import scala.scalajs.LinkingInfo
import scala.scalajs.js.annotation.JSExportTopLevel
import japgolly.scalajs.react.vdom.Implicits._

object Main {
  protected def getInstance(): this.type = this

  @JSExportTopLevel("entrypoint.main")
  def main(): Unit = {
    if (LinkingInfo.developmentMode) {
      hot.initialize()
    }

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    val rootWrapper = AppCircuit.connect(m => m)

    val _ = rootWrapper(ClassDiagramRoot.apply).renderIntoDOM(container)
    // start the application by dispatching a Reset action
    // AppCircuit(Reset)
  }
}
