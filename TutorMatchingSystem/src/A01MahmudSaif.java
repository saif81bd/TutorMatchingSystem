import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author 		Saif Mahmud
 * @id          7808507
 * @version     Jun. 9, 2020
 * @assignment	01
 * @purpose		Design A tutor finding program.
 */
public class A01MahmudSaif 
{
	/**
	 * These are the tables.
	 */
	private static LinkedList tutorList = new LinkedList();
	private static LinkedList studentList = new LinkedList();
	private static LinkedList topicsList = new LinkedList();
	private static LinkedList appointments = new LinkedList();
	private static Boolean quitFound = false; // if the end/quit command was found or not.
	/**
	 * @param args Takes String input(s) from the terminal as an array
	 */
	public static void main(String[] args) 
	{
		String fileName = args[0];
		String[] oneLine;
		ArrayList<String> compareCommand = new ArrayList<String>();
		try 
		{
			Scanner sc = new Scanner(new File(fileName));
			while(sc.hasNext())
			{
				oneLine = sc.nextLine().trim().split("\\s+");
				
				//this is just to place a println while command changes for better looks 
				//at the terminal.
				compareCommand.add(oneLine[0]);
				if(compareCommand.size() == 2)
				{
					if(!compareCommand.get(0).equals(compareCommand.get(1))){
						System.out.println();
					} else {
						if (compareCommand.get(0).equals("TUTORREPORT") || 
								compareCommand.get(0).equals("TUTORREPORT")) {
							System.out.println();
						}
					}
					compareCommand.remove(0);
				}
				perseLine(oneLine, sc);
			}
			if(!quitFound)
			{
				System.out.println("END command was missing.");
				sc.close();
				System.exit(0);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param oneLine Single line of the input file to process.
	 * @param sc The scanner variable
	 */
	private static void perseLine(String[] oneLine, Scanner sc) {
		if(oneLine[0].equals("TUTOR")){
			processTutor(oneLine);
		} else if(oneLine[0].equals("STUDENT")) {
			processStudent(oneLine);
		} else if(oneLine[0].equals("TOPIC")) {
			processTopic(oneLine);
		} else if(oneLine[0].equals("REQUEST")) {
			processRequest(oneLine);
		} else if(oneLine[0].equals("STUDENTREPORT")) {
			processStdReport(oneLine);
		} else if(oneLine[0].equals("TUTORREPORT")) {
			processTutorReport(oneLine);
		} else if(oneLine[0].equals("QUIT")) {
			processEnd(oneLine, sc);
		} else if(oneLine[0].startsWith("#")){
			// do nothing
		} else {
			System.out.println("Invalid command. Check the command input file.");
		}
	}
	/**
	 * @param oneLine Single line of the input file to process.
	 * @param sc The scanner variable to close it
	 */
	private static void processEnd(String[] oneLine, Scanner sc) 
	{
		quitFound = true;
		System.out.println("BYE!");
		sc.close();
		System.exit(0);
	}	
	/**
	 * @param oneLine Single line of the input file to process.
	 */
	private static void processTutorReport(String[] oneLine) 
	{
		String tutorID = oneLine[1];
		if(tutorList.searchList(tutorID))
		{
			System.out.println("Tutor Report:");
			appointments.tutorReport(tutorID);
		} else {
			System.out.println("Failed to show report of "+ tutorID + ". Tutor not Found");
		}
	}
	/**
	 * @param oneLine Single line of the input file to process.
	 */
	private static void processStdReport(String[] oneLine) 
	{
		String studentID = oneLine[1];
		if(studentList.searchList(studentID))
		{
			System.out.println("Student Report:");
			appointments.stdReport(studentID);
		} else {
			System.out.println("Failed to show report of "+ studentID + ". Student not Found");
		}
	}
	/**
	 * @param oneLine Single line of the input file to process.
	 */
	private static void processRequest(String[] oneLine) 
	{
		int inputHrs = Integer.parseInt(oneLine[3]);
		String studentID = oneLine[1];
		Student studentObj = (Student) studentList.getObjByName(studentID);
		String topicName = oneLine[2];
		Topic topicObj = null;
		Boolean failed = false;
		Boolean succeded = false;
		if(inputHrs > 0 && inputHrs < 2000)
		{
			if(studentList.searchList(studentID))
			{
				if(topicsList.searchList(topicName))
				{	
					do {
						Node curr = topicsList.top;
						if(topicsList.checkAvailableHrs(topicName, inputHrs))
						{	
							Tutor tutor = topicsList.tutorFilter(topicName);
							while(curr != null)
							{
								if(curr.item.getName().equals(topicName) && 
									curr.item.getTutor().getName().equals(tutor.getName()))
								{
									topicObj = (Topic) curr.item;
								}
								curr = curr.next;
							}
							int tutorHrs = tutor.getTutorAvailableHrs();
							if(tutorHrs < inputHrs)
							{
								Appointment newAppointment = 
										new Appointment(tutor, studentObj, topicObj, tutorHrs);
								appointments.add(new Node(newAppointment, null));
								tutor.reduceHrs(tutorHrs); //makes it 0
								inputHrs = inputHrs - tutorHrs;
								succeded = false; //means need next tutor
							} 
							else if(tutorHrs == inputHrs){
								Appointment newAppointment = 
										new Appointment(tutor, studentObj, topicObj, tutorHrs);
								appointments.add(new Node(newAppointment, null));
								tutor.reduceHrs(tutorHrs);
								inputHrs = 0;
								succeded = true;
							} else if(tutorHrs > inputHrs){ // tutor have enough hrs. no need of next tutor
								Appointment newAppointment = new Appointment(tutor, studentObj, 
										topicObj, inputHrs);
								appointments.add(new Node(newAppointment, null));
								tutor.reduceHrs(inputHrs);
								inputHrs = 0;
								succeded = true;
							}
							if(succeded)
							{
								System.out.println("CONFIRMED. Request of " + studentID + " on topic "
										+ topicName + " created successfully.");
							}
						} else {
							System.out.println("FAILED. Not available hours found requested by the student "
									+ studentID +".");
							failed = true;
						}
					} while(!failed && !succeded && inputHrs != 0);
				} else {
					System.out.println("FAILED. Topic: " + topicName + " NOT FOUND");
				}
			} else {
				System.out.println("FAILED. No student foind with StudentID: " + studentID);
			}
		} else {
			System.out.println("FAILED. Invalid input hours: "+inputHrs+". Must be between 0 to 2000");
		}
	}
	/**
	 * @param oneLine Single line of the input file to process.
	 */
	private static void processTopic(String[] oneLine) 
	{
		int price = Integer.parseInt(oneLine[3]);
		if(oneLine[1].length() < 80 )
		{
			if(price > 0 && price < 1000)
			{
				if(tutorList.searchList(oneLine[2]))
				{
					if(!topicsList.duplicateTopic(oneLine[1], oneLine[2]))
					{
						Tutor tutor = (Tutor) tutorList.getObjByName(oneLine[2]);
						if(tutor.getTutorAvailableHrs() > 0)
						{
							TutorMatchSystem topic = new Topic(oneLine[1], tutor, price);
							Node n = new Node(topic, null);
							topicsList.add(n);

							System.out.println("CONFIRMED. TOPIC with userid " + topic.getName() + " and tutor id " + 
									tutor.getName() + " successfully created.");
						} else { 
							System.out.println("Tutor " + tutor.getName() + " doesn't have enough "
									+ "hours to allocate");
						}
					} else {
						System.out.println("FAILED. DUPLICATE TOPIC with the same topic and tutor.");
					}
				} else {
					System.out.println("FAILED. No tutor found with tutorID: " + oneLine[2]);
				}
			} else { 
				System.out.println("Invalid PRICE. Must be between 0 and 1000");
			}
		} else {
			System.out.println("Invalid TOPIC. Must be below 80 non-whitespaced characters");
		}
	}
	/**
	 * @param oneLine Single line of the input file to process.
	 */
	private static void processStudent(String[] oneLine) 
	{
		if(oneLine[1].length() < 80)
		{
			if(!studentList.searchList(oneLine[1]))
			{
				TutorMatchSystem studentObj = new Student(oneLine[1]);
				Node n = new Node(studentObj, null);
				studentList.add(n);
				System.out.println("CONFIRMED. Student with userid " + studentObj.getName() + " successfully created.");
			} else {
				System.out.println("DUPLICATE Student with userid " + oneLine[1] + ".");
			}
		} else {
			System.out.println("Invalid userid. Must be below 80 non-whitespaced characters");
		}
	}
	/**
	 * @param oneLine Single line of the input file to process.
	 */
	private static void processTutor(String[] oneLine) 
	{	
		if(oneLine[1].length() < 80)
		{
			if(!tutorList.searchList(oneLine[1]))
			{
				TutorMatchSystem tutorObj = new Tutor(oneLine[1], Integer.parseInt(oneLine[2]));
				Node n = new Node(tutorObj, null);
				tutorList.add(n);
				System.out.println("CONFIRMED. Tutor with userid " + tutorObj.getName() + " successfully created.");
				
			} else {
				System.out.println("DUPLICATE Tutor with userid " + oneLine[1] + ".");
			}
		} else {
			System.out.println("FAILED. Invalid userid: " +oneLine[1]+". Must be below 80 non-whitespaced characters");
		}
	}
}//class
