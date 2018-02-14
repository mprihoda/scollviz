package net.prihoda.scala.coll.web

import org.scalajs.dom._
import japgolly.scalajs.react.vdom.Implicits._

object Visualization {
  protected def getInstance(): this.type = this

  def main(args: Array[String]): Unit = {
    val root = document.getElementById("root")
    val rootWrapper = AppCircuit.connect(m => m)
    val _ = rootWrapper(ClassDiagramRoot.apply).renderIntoDOM(root)
    // start the application by dispatching a Reset action
    // AppCircuit(Reset)
  }
}
