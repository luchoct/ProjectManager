package com.luisgal.proman

import com.luisgal.proman.Employee

class Project {
  String name;
  String code;
  
  Employee technicalLead;
  Employee projectManager;

  Date deliveryDate;
  Phase phase;
  Integer priority;

  static mapping = {
    name index: 'Project_Name_Idx'
    code index: 'Project_Code_Idx'
    sort "priority"
  }

  static constraints = {
    name maxSize: 40, blank: false
    code maxSize: 20, blank: false, unique: true
    technicalLead nullable: true
    projectManager nullable: true
    deliveryDate nullable: true
    priority min: 1
  }

  int compareTo(obj) {
    code.compareTo(obj.code);
  }

  String toString() {
    return new StringBuilder(200) << 'code ' << code << ' name ' << name << ' priority ' << priority
  }
}
