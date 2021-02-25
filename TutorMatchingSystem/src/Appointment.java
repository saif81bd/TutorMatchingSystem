/**
 * @author 		Saif Mahmud
 * @id          7808507
 * @version     Jun. 9, 2020
 * @assignment	01
 * @purpose		Design A tutor finding program.
 */
public class Appointment extends TutorMatchSystem
{

	private Student studentObj;
	private Topic topicObj;
	private int allocatedHrs;
	private Tutor tutorObj;
	/**
	 * @param tutorObj Tutor. Takes the tutor object.
	 * @param studentObj Student. Takes the tutor object.
	 * @param topicObj Topic. Takes the topic object.
	 * @param allocatedHrs int. Total allocated hours by the student and the teacher.
	 */
	public Appointment(Tutor tutorObj, Student studentObj, Topic topicObj, int allocatedHrs) 
	{
		this.tutorObj = tutorObj;
		this.studentObj = studentObj;
		this.topicObj = topicObj;
		this.allocatedHrs = allocatedHrs;
	}
	public Student getStudent() {
		return studentObj;
	}
	public Topic getTopic() {
		return topicObj;
	}
	public int getAllocatedHrs() {
		return allocatedHrs;
	}
	public Tutor getTutor() {
		return tutorObj;
	}
	public String getName() {
		return tutorObj.getName();
	}
}//class
