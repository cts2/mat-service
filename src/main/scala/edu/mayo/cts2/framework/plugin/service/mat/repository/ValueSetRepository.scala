package edu.mayo.cts2.framework.plugin.service.mat.repository;

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import edu.mayo.cts2.framework.plugin.service.mat.model.ValueSet
import scala.reflect.BeanProperty
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import edu.mayo.cts2.framework.plugin.service.mat.model.ValueSetEntry
import edu.mayo.cts2.framework.plugin.service.mat.model.ValueSetVersion

@Repository
@Transactional
trait ValueSetRepository extends CrudRepository[ValueSet, String] {

  def findAll(pageable: Pageable): Page[ValueSet]
  
  @Query("select vs from ValueSet vs where upper(vs.name) like upper(:query) or upper(vs.formalName) like upper(:query) " +
  		"or upper(vs.id.oid) like upper(:query)")
  def findByAnyLikeIgnoreCase(@Param("query") query:String, pageable: Pageable): Page[ValueSet]

  @Query("select vs.currentVersion.id from ValueSet vs where vs.name = :name")
  def findCurrentVersionIdByName(@Param("name") name:String): String
  
  @Query("select vsv from ValueSetVersion vsv where vsv.valueSet.name = :name and (vsv.id = :id or vsv.versionId = :id)")
  def findVersionByIdOrVersionIdAndValueSetName(@Param("name") name:String, @Param("id") id:String): ValueSetVersion

  def findByNameLikeIgnoreCase(query:String, pageable: Pageable): Page[ValueSet]
  
  def findByFormalNameLikeIgnoreCase(query:String, pageable: Pageable): Page[ValueSet]
  
  def findOneByName(query:String): ValueSet

  @Query("select distinct entries.codeSystem, entries.codeSystemVersion from ValueSet valueSet " +
  		"inner join valueSet.currentVersion.entries entries where valueSet.id.oid = :oid")
  def findCodeSystemVersionsByOid(@Param("oid") oid:String): java.util.Collection[Array[String]]
  
  @Query("select distinct entries.codeSystem, entries.codeSystemVersion from ValueSet valueSet " +
  		"inner join valueSet.currentVersion.entries entries where valueSet.name = :name")
  def findCodeSystemVersionsByName(@Param("name") name:String): java.util.Collection[Array[Any]]

}