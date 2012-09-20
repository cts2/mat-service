package edu.mayo.cts2.framework.plugin.service.mat.model

import javax.persistence.Entity
import scala.reflect.BeanProperty
import java.util.ArrayList
import javax.persistence.OneToMany
import javax.persistence.ElementCollection
import javax.persistence.FetchType
import javax.persistence.Id
import scala.collection.JavaConversions._
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column
import java.util.Date
import java.util.Calendar

@Entity
class ValueSet extends Equals {

  @Id
  @BeanProperty
  var oid: String = _

  @BeanProperty
  @Column(unique = true, nullable = false)
  var name: String = _

  @BeanProperty
  var version: String = _

  @BeanProperty
  var formalName: String = _

  @BeanProperty
  var valueSetDeveloper: String = _

  @BeanProperty
  var source: String = _

  @BeanProperty
  var valueSetType: String = _

  @BeanProperty
  var binding: String = _

  @BeanProperty
  var status: String = _

  @BeanProperty
  var revisionDate: Calendar = _

  @BeanProperty
  var qdmCategory: String = _

  @ElementCollection
  var entries: java.util.List[ValueSetEntry] = new ArrayList[ValueSetEntry]()

  @ElementCollection
  var includesValueSets: java.util.List[String] = new ArrayList[String]()

  def canEqual(other: Any) = {
    other.isInstanceOf[edu.mayo.cts2.framework.plugin.service.mat.model.ValueSet]
  }

  override def equals(other: Any) = {
    other match {
      case that: edu.mayo.cts2.framework.plugin.service.mat.model.ValueSet => that.canEqual(ValueSet.this) && oid == that.oid
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime + oid.hashCode
  }

}