/**
 * @author 		Saif Mahmud
 * @id          7808507
 * @version     Jun. 9, 2020
 * @assignment	01
 * @purpose		Design A tutor finding program.
 */
public abstract class TutorMatchSystem 
{
	//this method belongs to all classes
	public abstract String getName();
	
	public Student getStudent() {
		return this.getStudent();
	}
	public int getAllocatedHrs() {
		return this.getAllocatedHrs();
	}
	public int getTutorPricePerHr() 
	{
		return this.getTutorPricePerHr();
	}
	public int getTutorAvailableHrs() 
	{
		return this.getTutorAvailableHrs();
	}
	public Tutor getTutor()
	{
		return this.getTutor();
	}
	public void reduceHrs(int hrs) 
	{
		this.reduceHrs(hrs);
	}
	public Topic getTopic() 
	{
		return this.getTopic();
	}
}//class
