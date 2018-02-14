package net.prihoda.scala.coll.web

import diode._
import diode.react.ReactConnector

case class ClassDescriptor(
    pkg: String,
    name: String
)

// Define the root of our application model
case class ClassDiagram(classes: Seq[ClassDescriptor])

// Define actions
case class Increase(amount: Int) extends Action

case class Decrease(amount: Int) extends Action

case object Reset extends Action

/**
  * AppCircuit provides the actual instance of the `RootModel` and all the action
  * handlers we need. Everything else comes from the `Circuit`
  */
object AppCircuit
    extends Circuit[ClassDiagram]
    with ReactConnector[ClassDiagram] {
  // define initial value for the application model
  def initialModel = ClassDiagram(
    Seq(
      ClassDescriptor("scala.collection", "GenTraversableOnce")
    )
  )

  override val actionHandler: HandlerFunction = (_, _) => None

  /*
  // zoom into the model, providing access only to the
  val counterHandler = new ActionHandler(zoomTo(_.counter)) {
    override def handle = {
      case Increase(a) => updated(value + a)
      case Decrease(a) => updated(value - a)
      case Reset       => updated(0)
    }
  }
   */

  /*
    // without the ActionHandler class, we would define the handler like this
    override val actionHandler: HandlerFunction =
      (model, action) => action match {
        case Increase(a) => Some(ModelUpdate(model.copy(counter = model.counter + a)))
        case Decrease(a) => Some(ModelUpdate(model.copy(counter = model.counter - a)))
        case Reset => Some(ModelUpdate(model.copy(counter = 0)))
        case _ => None
      }
 */
}
