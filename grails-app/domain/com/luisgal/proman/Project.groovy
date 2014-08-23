package com.luisgal.proman

import com.luisgal.proman.Employee

class Project {
  String code;
  String name;
  
  Date deliveryDate;
  
  String phase;
  Integer priority;

  Employee technicalLead;
  Employee projectManager;

  static mapping = {
    code index: 'Project_Code_Idx'
    name index: 'Project_Name_Idx'
    sort "priority"
  }

  static constraints = {
    code maxSize: 20, blank: false, unique: true
    name maxSize: 40, blank: false
    deliveryDate nullable: true
    //The only purpose of having phase here without any constraints is ensuring the order of the generated fields in view.
    phase inList: ['briefing', 'scoping', 'interaction', 'development', 'qa', 'release']
    priority min: 1
    technicalLead nullable: true
    projectManager nullable: true
  }

  int compareTo(obj) {
    code.compareTo(obj.code);
  }

  String toString() {
    return new StringBuilder(200) << 'code ' << code << ' name ' << name << ' priority ' << priority
  }
}
