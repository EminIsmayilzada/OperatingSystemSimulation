import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class OperatingSystemSimulation {

	public static void main(String[] args) {
		System.out.println("Listed Tasks according to their Arrival Time(2):");
		

		LinkedSortedList<Task> taskList=new LinkedSortedList<Task>();
		LinkedPriorityQueue<Task> priorityTaskList=new LinkedPriorityQueue<Task>();
		StackLinkedList<Task>burstTimeTaskList=new StackLinkedList<Task>();
		taskList=readFile("tasks.txt");
		for (int i = 1; i <=taskList.getLength(); i++) {				//creating and printing the sorted list of the all tasks according to their arrival time
			Task task= taskList.getEntry(i);
			System.out.println("			"+task.toString());
		}
		for (int  i = 1; i <= taskList.getLength(); i++) {				//creating the waiting priority line
			Task prioritizedTask=taskList.getEntry(i);
			priorityTaskList.add(prioritizedTask);
		}
		for (int i = 1; i <= taskList.getLength(); i++) {				//creating the pile of waiting burst time
			Task burstTask=taskList.getEntry(i);
			burstTimeTaskList.push(burstTask);
			
		}
		
		while (!burstTimeTaskList.isEmpty()&&!priorityTaskList.isEmpty()) {
			System.out.println("Waiting priority line according to the execution time (4.a.)");
			System.out.println("            Remaining tasks after every 5 execution in the waiting priority line (6)");
			for (int i = 1; i <=priorityTaskList.getSize(); i++) {			// printing the remaining tasks according to their priorities
				Task remainingPtask=priorityTaskList.getEntry(i);
				System.out.println("              "+remainingPtask.toString());
			}
			for (int i = 1; i <=5; i++) {									// deleting tasks 5 by 5 according to their priorities 
				Task pTask=priorityTaskList.remove();
			}
			System.out.println("Pile of waiting burst time according to the execution time (4.b.)");
			System.out.println("            Remaining tasks after every 5 execution in the waiting priority line (6)");
			for (int i = 1; i <=burstTimeTaskList.getSize(); i++) {			// printing the remaining tasks according to their burst times
				Task remainingBtask=burstTimeTaskList.getEntry(i);
				System.out.println("              "+remainingBtask.toString());
			}
			for (int i = 1; i <=5; i++) {									// deleting tasks 5 by 5 according to their burst times
				Task bTask=burstTimeTaskList.pop();
				
			}
		}
		
		
	}
	public static LinkedSortedList<Task> readFile(String fileName) {
	    LinkedSortedList<Task> sortedTaskList = new LinkedSortedList<Task>();

	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(fileName));
	        String line;
	        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm");
	        while ((line = reader.readLine()) != null) {
	            String[] taskParts = line.split(",");
	            String taskType = taskParts[0].trim();
	            int burstTime = Integer.parseInt(taskParts[1].trim());
	            LocalDateTime arrivalDateTime = LocalDateTime.parse(taskParts[2].trim() + "," + taskParts[3].trim(), formatted);
	            Task newTask = new Task(taskType, burstTime, arrivalDateTime);
	            sortedTaskList.add(newTask);
	        }
	        reader.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return sortedTaskList;
	}
	 
		}
	

