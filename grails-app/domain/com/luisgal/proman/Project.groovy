package com.luisgal.proman

import com.luisgal.proman.Employee

class Project {
  Integer priority;
  String code;
  String name;
  
  Date deliveryDate;
  
  String phase;

  Employee technicalLead;
  Employee projectManager;

  static mapping = {
    code index: 'Project_Code_Idx'
    name index: 'Project_Name_Idx'
    sort "priority"
  }

  static constraints = {
    priority min: 1, unique: true
    code maxSize: 20, blank: false, unique: true
    name maxSize: 40, blank: false
    deliveryDate nullable: true
    phase inList: ['briefing', 'scoping', 'interaction', 'development', 'qa', 'release']
    technicalLead nullable: true
    projectManager nullable: true
  }

  String toString() {
    return new StringBuilder(200) << 'code ' << code << ' name ' << name << ' priority ' << priority
  }
}
