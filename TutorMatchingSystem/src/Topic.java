/**
 * @author 		Saif Mahmud
 * @id          7808507
 * @version     Jun. 9, 2020
 * @assignment	01
 * @purpose		Design A tutor finding program.
 */
public class Topic extends TutorMatchSystem
{
	private String topicName;
	private Tutor tutor;
	private int pricePerHr;
	/**
	 * @param topicName String. Name of the topic.
	 * @param tutorObj Tutor. Takes the tutor object.
	 * @param pricePerHr int. price per hour on this topic by the tutor.
	 */
	public Topic(String topicName, Tutor tutorObj, int pricePerHr)
	{
		this.topicName = topicName;
		this.tutor = tutorObj;
		this.pricePerHr = pricePerHr;
	}
	public Tutor getTutor()
	{
		return this.tutor;
	}
	public String getName() 
	{
		return topicName;
	}
	public int getTutorPricePerHr() 
	{
		return this.pricePerHr;
	}
}//class
