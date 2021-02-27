/**
 * @author 		Saif Mahmud
 * @id          7808507
 * @version     Jun. 9, 2020
 * @assignment	01
 * @purpose		Design A tutor finding program.
 */
public class Student extends TutorMatchSystem
{
	private String name;
	
	/**
	 * @param name The name of the student
	 */
	public Student(String name) 
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
}//class
