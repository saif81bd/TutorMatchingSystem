import java.util.ArrayList;
/**
 * @author 		Saif Mahmud
 * @id          7808507
 * @version     Jun. 9, 2020
 * @assignment	01
 * @purpose		Design A tutor finding program.
 */
public class LinkedList 
{
	Node top;
	public LinkedList()
	{
		top = null;
	}
	/**
	 * @param item Takes an node item and add it to the table/LinkedList
	 */
	public void add(Node item)
	{
		item.next = this.top;
		this.top = item;
	}
	/**
	 * @param name String. Search whole table for this variable as the name of the item.
	 * @return Boolean. True if found in the table.
	 */
	public Boolean searchList(String name)
	{
		Boolean found = false;
		Boolean firstItem = false;
		Node curr = this.top;
		
		if(curr == null)
		{
			found = false;
			firstItem = true;
		}
		if(!firstItem && !found)
		{
			do { 
				if(curr.item.getName().equals(name))
				{
					found = true;
				}
				curr = curr.next;
			} while(curr != null && !found);
		}
		return found;
	}//method
	/**
	 * @param name String. Search whole table for this variable as the name of the item.
	 * @return TutorMatchSystem if found, null otherwise.
	 */
	public TutorMatchSystem getObjByName(String name)
	{
		TutorMatchSystem out = null;
		Boolean found = false;
		Boolean firstItem = false;
		Node curr = this.top;
		
		if(curr == null)
		{
			found = false;
			firstItem = true;
		}
		if(!firstItem && !found)
		{
			do {
				if(curr.item.getName().equals(name)) 
				{
					found = true;
					out = curr.item;
				}
				curr = curr.next;
			} while(curr != null && !found);
		}
		return out;
	}//method
	/**
	 * @param topicName String.
	 * @param tutorID String. 
	 * Search whole table for an object containing these two variable togather.
	 * @return Boolean true if found.
	 */
	public Boolean duplicateTopic(String topicName, String tutorID)
	{
		Boolean isDuplicate = false;
		Boolean firstItem = false;
		Node curr = this.top;
		if(curr == null)
		{
			firstItem = true;
		}
		if(!firstItem && !isDuplicate)
		{
			do {
				if(curr.item.getName().equals(topicName) && curr.item.getName().equals(tutorID))
				{
					isDuplicate = true;
				}
				curr = curr.next;
			} while (!isDuplicate && curr != null);
		}
		return isDuplicate;
	}
	/**
	 * @param topicName String.
	 * Finds the tutor with this topicName variable and returns the tutor with most suitable tutor 
	 * with applying this filters accordingly: <br>
	 * 1. Lowest price. If a tie then, <br>
	 * 2. Max hours. If again a tie then, <br>
	 * 3. First userid. 
	 * @return Tutor with appropiate attribites.
	 */
	public Tutor tutorFilter(String topicName)
	{
		Tutor out = this.top.item.getTutor();
		ArrayList<Topic> topics = new ArrayList<Topic>();
		Node curr = this.top; //topicsList
		int lowestPrice = Integer.MAX_VALUE;
		while(curr != null)
		{
			if(curr.item.getName().equals(topicName) && 
					curr.item.getTutor().getTutorAvailableHrs() > 0)
			{
				topics.add((Topic) curr.item);
				//get lowest price
				if(curr.item.getTutorPricePerHr() < lowestPrice)
				{
					lowestPrice = curr.item.getTutorPricePerHr();
				}
			}
			curr = curr.next;
		}
		out = topics.get(0).getTutor();
		if(topics.size() > 1)
		{
			// have to go by lowest price
			for (int i = 0; i < topics.size(); i++)
			{
				if(topics.get(i).getTutorPricePerHr() != lowestPrice) 
				{
					topics.remove(i);
					i = i-1;
				}
			}
			if(topics.size() > 1)
			{
				// have to go by the maxHrs
				for (int i = 1; i < topics.size(); i++)
				{
					if(topics.get(i).getTutor().getTutorAvailableHrs() < 
						topics.get(i-1).getTutor().getTutorAvailableHrs()){
						topics.remove(i);
						i = i-1;
					}
				}
			}
			if(topics.size() > 1)
			{
				//have to go by name
				for (int i = 1; i < topics.size(); i++)
				{
					if(topics.get(i).getTutor().getName().compareToIgnoreCase(topics.get(i-1).getTutor().getName()) 
							< 0 ){
						topics.remove(i);
						i = i-1;
					}
				}
			}
		}
		out = topics.get(0).getTutor();
		return out;
	}
	/**
	 * @param studentID Takes student id and prints this student's report containing all the 
	 * appointment.
	 */
	public void stdReport(String studentID)
	{
		ArrayList<Appointment> temp = new ArrayList<Appointment>();
		Node curr = this.top; // this = appointments
		
		int totalTutoringHrs = 0;
		int totalCostOfTutoring = 0;

		while(curr != null)
		{
			if(curr.item.getStudent().getName().equals(studentID))
			{
				temp.add((Appointment) curr.item);
			}
			curr = curr.next;
		}
		if(temp.size() > 0)
		{
			System.out.println("Report for Student " + temp.get(0).getStudent().getName());
			for (int i = 0; i < temp.size(); i++)
			{
				String tutorName = temp.get(i).getTutor().getName();
				String topicName = temp.get(i).getTopic().getName();
				int hoursAllocated = temp.get(i).getAllocatedHrs();
				int costPerHr = temp.get(i).getTopic().getTutorPricePerHr();
				
				int totalCost = hoursAllocated*costPerHr;
				totalTutoringHrs = totalTutoringHrs + hoursAllocated;
				totalCostOfTutoring = totalCostOfTutoring + totalCost;
				System.out.println("Appointment: Tutor : " + tutorName + ", " +
									"Topic: " + topicName + ", " +
									"hours: " + hoursAllocated + ", " +
									"total Cost : " + totalCost);
			}
			System.out.println("Total number of hours of tutoring: " + totalTutoringHrs);
			System.out.println("Total cost of tutoring: " + totalCostOfTutoring);
		} else {
			System.out.println("Student: " + studentID + " hasn't been assigned with anyone.");
		}
	}
	/**
	 * @param tutorID Takes student id and prints this tutor's report containing all the 
	 * appointments.
	 */
	public void tutorReport(String tutorID)
	{
		ArrayList<Appointment> temp = new ArrayList<Appointment>();
		Node curr = this.top; // this = requestList
		int totalTutoringHrs = 0;
		int totalRevenueOfTutoring = 0;

		while(curr != null)
		{
			if(curr.item.getTopic().getTutor().getName().equals(tutorID))
			{
				temp.add((Appointment) curr.item);
			}
			curr = curr.next;
		}
		if(temp.size() > 0)
		{
			String tutorName = temp.get(0).getTutor().getName();
			System.out.println("Report for Tutor: " + tutorName);
			for (int i = 0; i < temp.size(); i++)
			{
				String studentName = temp.get(i).getStudent().getName();
				String topicName = temp.get(i).getTopic().getName();
				int allocatedHrs = temp.get(i).getAllocatedHrs();
				int costPerHr = temp.get(i).getTopic().getTutorPricePerHr();
				int revenue = allocatedHrs*costPerHr;
				totalTutoringHrs = totalTutoringHrs + allocatedHrs;
				totalRevenueOfTutoring = totalRevenueOfTutoring + revenue;
				System.out.println("Appointment: Student : " + studentName + ", " +
									"Topic: " + topicName + ", " +
									"hours: " + allocatedHrs + ", " +
									"total revenue : " + revenue);
			}
			System.out.println("Total number of hours of tutoring: " + totalTutoringHrs);
			System.out.println("Total revenue from tutoring: " + totalRevenueOfTutoring);
		} else {
			System.out.println(tutorID + " hasn't been assigned with anyone");
		}
	}
	/**
	 * @param topicName String. Takes the topic name.
	 * @param hrsNeeded int. Takes the hours needed on this topic.
	 * @return Boolean true if enough hrs available on this topic. False otherwise.
	 */
	public Boolean checkAvailableHrs(String topicName, int hrsNeeded)
	{
		Boolean isAvailable = false;
		Node curr = this.top; // topicsList
		if(curr != null)
		{
			int availableHrs = 0;
			while(curr != null && !isAvailable)
			{
				if(curr.item.getName().equals(topicName));
				{
					availableHrs = availableHrs + curr.item.getTutor().getTutorAvailableHrs();
				}
				curr = curr.next;
				if(availableHrs >= hrsNeeded)
				{
					isAvailable = true;
				}
			}
		} else {
			System.out.println("Topic list empty =>checkAvailableHrs()");
		}
		return isAvailable;
	}
}// class