package edu.mayo.cts2.framework.plugin.service.mat.model

import javax.persistence.Entity
import javax.persistence.Embeddable
import scala.reflect.BeanProperty
import javax.persistence.Column
import javax.persistence.ElementCollection
import java.util.ArrayList
import java.util.UUID
import javax.persistence.Id

@Entity
class ValueSetProperty {
  
  @Id
  @BeanProperty
  var id: String = UUID.randomUUID.toString

  @BeanProperty
  var name: String = _

  @BeanProperty
  @Column(length = 1024)
  var value: String = _
  
  @ElementCollection
  var qualifiers: java.util.List[PropertyQualifier] = new ArrayList[PropertyQualifier]()

}