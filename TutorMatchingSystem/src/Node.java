package a01;

/**
 * @author 		Saif Mahmud
 * @id          7808507
 * @version     Jun. 9, 2020
 * @assignment	01
 * @purpose		Design A tutor finding program.
 */
public class Node 
{
	public TutorMatchSystem item;
	public Node next;
	
	public Node(TutorMatchSystem item, Node next)
	{
		this.item = item;
		this.next = next;
	}

}
