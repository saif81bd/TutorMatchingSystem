/**
 * @author 		Saif Mahmud
 * @id          7808507
 * @version     Jun. 9, 2020
 * @assignment	01
 * @purpose		Design A tutor finding program.
 */
public class Tutor extends TutorMatchSystem
{
	private String name;
	private int maxHours;
	/**
	 * @param name String. The name of the tutor.
	 * @param hours int. Hours offered by this tutor.
	 */
	public Tutor(String name, int hours) 
	{
		this.name = name;
		this.maxHours = hours;
	}
	public String getName()
	{
		return this.name;
	}
	public int getTutorAvailableHrs()
	{
		return this.maxHours;
	}
	public void reduceHrs(int hrs) 
	{
		this.maxHours = this.maxHours-hrs;
	}	
}//class
