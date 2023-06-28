import java.time.LocalDateTime;
import java.util.Comparator;

public class Task implements Comparable<Task>{
	private int burstTime;
	private String taskType;
	private LocalDateTime arrivalDateTime;
	public Task(String taskType, int burstTime, LocalDateTime arrivalDateTime) {
        this.taskType = taskType;
        this.burstTime = burstTime;
        this.arrivalDateTime = arrivalDateTime;
    }
	public int getBurstTime() {
		return burstTime;
	}
	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public LocalDateTime getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	
	public int getPriority() {
	        switch (taskType) {
	            case "security management":
	                return 6;
	            case "process management":
	                return 5;
	            case "memory management":
	                return 4;
	            case "user management":
	                return 3;
	            case "device management":
	                return 2;
	            case "file management":
	                return 1;
	           
	        }
	
        return 0;
	    }
	 @Override
	    public String toString() {
	        return "Task: " + taskType + ", Burst Time: " + burstTime + ", Arrival Time: " + arrivalDateTime;
	    }
	 public int compareTo(Task otherTask) {
	        // Compare based on arrival time and priority
	        if (this.arrivalDateTime.compareTo(otherTask.arrivalDateTime) != 0) {
	            return this.arrivalDateTime.compareTo(otherTask.arrivalDateTime);
	        } else {
	            return Integer.compare(otherTask.getPriority(), this.getPriority());
	        }
	    }
	
	}
