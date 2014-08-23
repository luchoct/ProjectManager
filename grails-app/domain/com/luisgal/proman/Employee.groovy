package com.luisgal.proman

class Employee {
  String code;
  String fullName;

  static mapping = {
    code index : 'Employee_Name_Idx'
    fullName index : true
  }

  static constraints = {
    code maxSize: 20, blank : false, unique : true
    fullName maxSize: 100, blank : false
  }

  String toString() {
    return fullName
  }
}
