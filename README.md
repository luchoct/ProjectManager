# Project Manager
## Functional Specification
Simple grails application to manage project information.

The application allows a user to create/edit/delete/view projects.

A project has the following information:

* project name (alphanumeric)
* project code (alphanumeric)
* tech lead (person's name)
* project manager (person's name)
* delivery date
* current phase (one of briefing/scoping/interaction/development/qa/release)
* priority (numerical)
	
The priority field goes from 1 (highest) upwards and is dependant on the number of projects (e.g. only one project is number 1 priority).
 
Kind of like a 'top of the pops' list. 

Any list view should default to ordering by priority.

## Technical Specification
* JDK 1.7.0_45
* Grails 2.3.6
* Groovy 2.2.1

## Run app
You can run the app in Heroku
```
https://pro-man.herokuapp.com
```

# Changelog
* 2014-08-23 Version 1.0 Initial version

# Areas of improvement


