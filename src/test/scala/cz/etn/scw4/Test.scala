package cz.etn.scw4

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

import Language._
import SkillLevel._

class Test extends FunSuite with ShouldMatchers {

  val zdenek = new Programmer("Zdenek", 20, Map(Java -> Noob, Scala -> Noob))
  val pajoslaff = new Programmer("Pajoslaff", 21, Map(Java -> Noob, Scala -> Noob))

  test("one element list") {
    Item(zdenek, None).length should be(1)
  }

  test("two elements list") {
    Item(zdenek, Some(Item(pajoslaff, None))).length should be(2)
  }

  test("empty queue") {
    val q = new Queue
    q.size should be(0)

    evaluating { q.dequeue } should produce[NoSuchElementException]
  }

  test("one element queue") {
    val q: Queue = new Queue
    val q2 = q.enqueue(zdenek)
    q2.size should be(1)

    val (q3, pr) = q2.dequeue
    q3.size should be(0)
    pr should be(zdenek)
  }

  test("two elements queue") {

    //    val q = new Queue().enqueue(zdenek).enqueue(pajoslaff)
    val paj = Item(pajoslaff, None)
    val linkedList = Item(zdenek, Some(paj))
    val q = new Queue(Some(linkedList), Some(paj))
    q.size should be(2)

    val (q1, pr1) = q.dequeue
    val (q2, pr2) = q1.dequeue
    pr1 should be (zdenek)
    pr2 should be (pajoslaff)
    q2.size should be (0)
  }

}