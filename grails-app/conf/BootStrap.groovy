
import com.luisgal.proman.Employee
import com.luisgal.proman.Project

class BootStrap {

  def init = { servletContext ->
    // Check whether the test data already exists.
    if (!Project.count() && !Employee.count()) {
      def robert = new Employee(code: 'EMP_1', fullName: 'Robert C. Martin').save(failOnError: true)
      def joshua = new Employee(code: 'EMP_2', fullName: 'Joshua Bloch').save(failOnError: true)
      def david = new Employee(code: 'EMP_3', fullName: 'David Hicks').save(failOnError: true)
      def steve = new Employee(code: 'EMP_4', fullName: 'Steve Jobs').save(failOnError: true)
      new Project(code: 'PRY-2', name: 'Project Manager App', technicalLead: joshua, projectManager: steve,
        deliveryDate: new GregorianCalendar(2014, Calendar.AUGUST, 24).time, phase: 'release', priority: 1).save(failOnError: true)
      new Project(code: 'PRY-1', name: 'Tetris', technicalLead: robert, projectManager: david,
      deliveryDate: new GregorianCalendar(2014, Calendar.SEPTEMBER, 30).time, phase: 'briefing', priority: 2).save(failOnError: true)
    }
  }
  def destroy = {
  }
}
