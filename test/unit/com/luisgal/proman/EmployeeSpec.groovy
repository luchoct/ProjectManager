package com.luisgal.proman

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Employee)
class EmployeeSpec extends Specification {

  def setup() {
  }

  def cleanup() {
  }
  
  def "test toString"() {
    when: 'creating an employee'
    //I do not invoke getValidEmployee method for getting more readability.
    def employee = new Employee(code: 'anyCode', fullName: 'anyName')
      
    then: 'toString returns expected string'
      "anyName" == employee.toString()
  }

  def getValidEmployee() {
    new Employee(code: 'anyCode', fullName: 'anyName')
  }

  def "test saving a valid employee"() {
    given:
    mockForConstraintsTests Employee
      
    when: 'saving a valid employee'
    def employee = getValidEmployee()
    def savedEmployee = employee.save(flush:true)
      
    then: 'employee is saved'
    savedEmployee
  }

  def verifyValidationErrors(final Employee employee, final String propertyName, final String errorType) {
    !employee.validate() && employee.hasErrors() && employee.errors[propertyName] == errorType
  }
  
  //CODE TESTS
  
  def "test code is mandatory"() {
    given:
    mockForConstraintsTests Employee

    when: 'creating an employee without code'
    def employee = getValidEmployee()
    employee.code = null
      
    then: 'validation should fail'
    verifyValidationErrors(employee, 'code', 'nullable')
  }

  def "test code is NOT empty"() {
    given:
    mockForConstraintsTests Employee

    when: 'creating an employee with an empty code'
    def employee = getValidEmployee()
    employee.code = ''
      
    then: 'validation should fail'
    verifyValidationErrors(employee, 'code', 'blank')
  }

  def "test a code is too long"() {
    given:
    mockForConstraintsTests Employee
      
    when: 'creating an employee with 21 characters code'
    def employee = getValidEmployee()
    employee.code = '123456789012345678901'
      
    then: 'validation should fail'
    verifyValidationErrors(employee, 'code', 'maxSize')
  }
  
  //FULL NAME TESTS

  def "test fullName is mandatory"() {
    given:
    mockForConstraintsTests Employee

    when: 'creating an employee without full name'
    def employee = getValidEmployee()
    employee.fullName = null
      
    then: 'validation should fail'
    verifyValidationErrors(employee, 'fullName', 'nullable')
  }
  
  def "test fullName NOT empty"() {
    given:
    mockForConstraintsTests Employee

    when: 'creating an employee with an empty full name'
    def employee = getValidEmployee()
    employee.fullName = ''
      
    then: 'validation should fail'
    verifyValidationErrors(employee, 'fullName', 'blank')
  }
  
  def "test a fullName is too long"() {
    given:
    mockForConstraintsTests Employee

    when: 'creating an employee with 101 characters code'
    def employee = getValidEmployee()
    employee.fullName = '12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901'
      
    then: 'validation should fail'
    verifyValidationErrors(employee, 'fullName', 'maxSize')
  }

  // ENTITTY LEVEL TESTS
  
  def "test a duplicated code"() {
    given:
    mockForConstraintsTests Employee
      
    when: 'saved first employee with common code'
    def employee1 = new Employee(code: 'commonCode', fullName: 'aName')
    def savedEmployee1 = employee1.save(flush:true)

    then: 'saved employee1'
    savedEmployee1
    
    when: 'saved second employee with common code'
    def employee2 = new Employee(code: 'commonCode', fullName: 'anotherName')
    def savedEmployee2 = employee2.save(flush:true)

    then: 'validation of second employee should fail'
    !savedEmployee2
    !employee2.validate()
    employee2.hasErrors()
    employee2.errors['code'] == 'unique'
  }
}
