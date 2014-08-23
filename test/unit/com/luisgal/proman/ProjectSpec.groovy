package com.luisgal.proman

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Project)
class ProjectSpec extends Specification {

  def setup() {
  }

  def cleanup() {
  }

  def "test toString"() {
    when: 'creating a project'
    //I do not invoke getValidProject method for getting more readability.
    def project = new Project(code: 'CODE_1', name: 'Any Name', 
      technicalLead: new Employee(code: 'codeTC', fullName: 'nameTC'),
      projectManager: new Employee(code: 'codePM', fullName: 'namePM'),
      deliveryDate: new Date(), phase: Phase.BRIEFING, priority: 1)
      
    then: 'verify the toString result'
      "code CODE_1 name Any Name priority 1" == project.toString()
  }

  def getValidProject() {
    new Project(code: 'CODE_1', name: 'Any Name', 
      technicalLead: new Employee(code: 'codeTC', fullName: 'nameTC'),
      projectManager: new Employee(code: 'codePM', fullName: 'namePM'),
      deliveryDate: new Date(), phase: Phase.BRIEFING, priority: 1)
  }

  def "test saving a valid project"() {
    given:
    mockForConstraintsTests Project
      
    when: 'saving a valid project'
    def project = getValidProject()
    def savedProject = project.save(flush:true)
      
    then: 'saved project'
    savedProject
  }

  def verifyValidationErrors(final Project project, final String propertyName, final String errorType) {
    !project.validate() && project.hasErrors() && project.errors[propertyName] == errorType
  }
  
  // NAME TESTS
  
  def "test name is mandatory"() {
    given:
    mockForConstraintsTests Project

    when: 'creating a project without full name'
    def project = getValidProject()
    project.name = null;
      
    then: 'validation should fail'
    verifyValidationErrors(project, 'name', 'nullable')
  }

  def "test name is NOT empty"() {
    given:
    mockForConstraintsTests Project

    when: 'creating a project with an empty name'
    def project = getValidProject()
    project.name = '';
      
    then: 'validation should fail'
    verifyValidationErrors(project, 'name', 'blank')
  }

  def "test a name is too long"() {
    given:
    mockForConstraintsTests Project

    when: 'creating a project with 41 characters code'
    def project = getValidProject()
    project.name = '123456789012345678901234567890123456789012345678901234567890123456789012345678901'
      
    then: 'validation should fail'
    verifyValidationErrors(project, 'name', 'maxSize')
  }

  //CODE TESTS
  
  def "test code is mandatory"() {
    given:
    mockForConstraintsTests Project

    when: 'creating a project without code'
    def project = getValidProject()
    project.code = null;
      
    then: 'validation should fail'
    verifyValidationErrors(project, 'code', 'nullable')
  }

  def "test code is NOT empty"() {
    given:
    mockForConstraintsTests Project

    when: 'creating a project with an empty code'
    def project = getValidProject()
    project.code = '';
      
    then: 'validation should fail'
    verifyValidationErrors(project, 'code', 'blank')
  }

  def "test a code is too long"() {
    given:
    mockForConstraintsTests Project
      
    when: 'creating a project with 21 characters code'
    def project = getValidProject()
    project.code = '123456789012345678901'
      
    then: 'validation should fail'
    verifyValidationErrors(project, 'code', 'maxSize')
  }

  //TECHNICAL LEAD TESTS
  
  def "test technicalLead is NOT mandatory"() {
    given:
    mockForConstraintsTests Project

    when: 'creating a project without technicalLead'
    def project = getValidProject()
    project.technicalLead = null;
      
    then: 'validation should fail'
    project.validate()
  }

  //PROJECT MANAGER TESTS
  
  def "test projectManager is NOT mandatory"() {
    given:
    mockForConstraintsTests Project

    when: 'creating a project without projectManager'
    def project = getValidProject()
    project.projectManager = null;
      
    then: 'validation should fail'
    project.validate()
  }
  
  //DELIVERY DATE TESTS

  def "test deliveryDate is NOT mandatory"() {
    given:
    mockForConstraintsTests Project

    when: 'creating a project without deliveryDate'
    def project = getValidProject()
    project.deliveryDate = null;
      
    then: 'validation should fail'
    project.validate()
  }

  //PHASE TESTS
  
  def "test phase is mandatory"() {
    given:
    mockForConstraintsTests Project

    when: 'creating a project without phase'
    def project = getValidProject()
    project.phase = null;
      
    then: 'validation should fail'
    verifyValidationErrors(project, 'phase', 'nullable')
  }

  //PRIORITY TESTS
  
  def "test priority is mandatory"() {
    given:
    mockForConstraintsTests Project

    when: 'creating a project without priority'
    def project = getValidProject()
    project.priority = null;
      
    then: 'validation should fail'
    verifyValidationErrors(project, 'priority', 'nullable')
  }

  def "test a priority is too low"() {
    given:
    mockForConstraintsTests Project
      
    when: 'creating a project with too low priority'
    def project = getValidProject()
    project.priority = 0
      
    then: 'validation should fail'
    verifyValidationErrors(project, 'priority', 'min')
  }

  //ENTITY LEVEL TESTS
  
  def "test a duplicated code"() {
    given:
    mockForConstraintsTests Project
      
    when: 'saved first project with common code'
    def project1 = new Project(code: 'commonCode', name: 'aName', 
      technicalLead: new Employee(code: 'codeTC1', fullName: 'nameTC1'),
      projectManager: new Employee(code: 'codePM1', fullName: 'namePM1'),
      deliveryDate: new Date(), phase: Phase.BRIEFING, priority: 1)
    def savedProject1 = project1.save(flush:true)

    then: 'saved project1'
    savedProject1
    
    when: 'saved second project with common code'
    def project2 = new Project(code: 'commonCode', name: 'anotherName', 
      technicalLead: new Employee(code: 'codeTC2', fullName: 'nameTC2'),
      projectManager: new Employee(code: 'codePM2', fullName: 'namePM2'),
      deliveryDate: new Date(), phase: Phase.BRIEFING, priority: 1)
    def savedProject2 = project2.save(flush:true)

    then: 'validation of second project should fail'
    !savedProject2
    !project2.validate()
    project2.hasErrors()
    project2.errors['code'] == 'unique'
  }
}
