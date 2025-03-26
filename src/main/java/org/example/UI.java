package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UI {
private final ArrayList<Task> tasksList;
private final Scanner scanner = new Scanner(System.in);
private static final Logger log = LoggerFactory.getLogger(UI.class);

public UI() {
	tasksList = new ArrayList<>();
}
private void printMenu(){
	System.out.println("1. Create a task");
	System.out.println("2. Update a task");
	System.out.println("3. Mark a task as completed");
	System.out.println("4. Delete a task");
	System.out.println("5. Display tasks by priority");
	System.out.println("6. Change a task's priority");
	System.out.println("7. Display all tasks");
	System.out.println("8. Display all tasks that are not completed");
	System.out.println("9. Delete all tasks");

	System.out.println("10. Exit");
}

public void displayMenu() {
	try {
		int choice = 0;

		while (choice != 10) {
			printMenu();
			System.out.print("> ");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					createTask();
					break;
				case 2:
					renameTask();
					break;
				case 3:
					markTaskCompletedId();
					break;
				case 4:
					deleteTaskId();
					break;
				case 5:
					displayPriority();
					break;
				case 6:
					prioritizeTask();
					break;
				case 7:
					displayTasks();
					break;
				case 8:
					displayIncompleteTasks();
					break;
				case 9:
					deleteAllTasks();
					break;
			}
		}
	} catch (Exception e) {
		log.error("e: ", e);
		System.out.println("Entry must be a number");
	}
}

public void loadList(){
	tasksList.addAll(Objects.requireNonNull(TaskRepository.findQuery("select * from TASK")));

}

public void displayTasks() {
	System.out.println("\n");
	System.out.println("ID | Name | Completed | Priority");
	Objects.requireNonNull(TaskRepository.findQuery("select * from TASK")).forEach(task1 -> System.out.println(task1.getId() + " | " + task1.getName() + " | " + task1.isCompleted() + " | " + task1.getPriority()));
	System.out.println("\n");
}

private void displayIncompleteTasks() {
	System.out.println("ID | Name | Completed | Priority");
	System.out.println("All Tasks that are not completed:");
	var query = "select * from TASK where completed = false";
	Objects.requireNonNull(TaskRepository.findQuery(query)).forEach(task1 -> System.out.println(task1.getId() + " | " + task1.getName() + " | " + task1.isCompleted() + " | " + task1.getPriority()));
	System.out.println("\n");
}

private void displayPriority() {
	System.out.println("What Priority do you want to see? (Hi / Med / Low)");
	String answer = scanner.nextLine().toUpperCase();
	switch (answer) {
		case "HI":
			System.out.println("ID | Name | Completed | Priority");
			var queryHi = "select * from TASK where priority = 'HIGH'";
			Objects.requireNonNull(TaskRepository.findQuery(queryHi)).forEach(task1 -> System.out.println(task1.getId() + " | " + task1.getName() + " | " + task1.isCompleted() + " | " + task1.getPriority()));
			System.out.println("\n");
			break;
		case "MED":
			System.out.println(" ID | Name | Completed | Priority");
			var queryMed = "select * from TASK where priority = 'MEDIUM'";
			Objects.requireNonNull(TaskRepository.findQuery(queryMed)).forEach(task1 -> System.out.println(task1.getId() + " | " + task1.getName() + " | " + task1.isCompleted() + " | " + task1.getPriority()));
			System.out.println("\n");
			break;
		case "LOW":
			System.out.println(" ID | Name | Completed | Priority");
			var queryLow = "select * from TASK where priority = 'LOW'";
			Objects.requireNonNull(TaskRepository.findQuery(queryLow)).forEach(task1 -> System.out.println(task1.getId() + " | " + task1.getName() + " | " + task1.isCompleted() + " | " + task1.getPriority()));
			System.out.println("\n");
			break;
		default:
			System.out.println("Invalid Priority");
			break;
	}
}

private void deleteTaskId() {
	System.out.println("Enter the ID of the task you want to delete");
	int deleteId = scanner.nextInt();
	TaskRepository.deleteId(deleteId);
	this.tasksList.removeIf(task1 -> task1.getId() == deleteId);
	System.out.println("\n");
}

private void deleteAllTasks() {
	System.out.println("Really delete all tasks? (y/n)");
	String answer = scanner.nextLine();
	if (answer.equals("y")) {
		TaskRepository.DeleteAll();
		this.tasksList.clear();
		System.out.println("Tasks deleted");
	} else {
		System.out.println("Tasks not deleted");
	}
	System.out.println("\n");
}

private void createTask() {
	System.out.println("Enter the name of the task");
	String name = scanner.nextLine().toUpperCase();
	System.out.println("Enter the Priority of the task (High, Medium, Low)");
	String priority = scanner.nextLine().toUpperCase();
	while (!priority.equals("HIGH") && !priority.equals("MEDIUM") && !priority.equals("LOW")) {
		System.out.println("Priority must be High, Medium, or Low");
		priority = scanner.nextLine().toUpperCase();
		System.out.print("> ");
		System.out.println("\n");
	}
	boolean completed = false;
	Task task = new Task(0, name, completed, priority);
	TaskRepository.create(task);
	this.tasksList.add(task);
}

private void renameTask() {
	displayTasks();
	System.out.println("Enter the ID of the task you want to update");
	int oldId = scanner.nextInt();
	scanner.nextLine();
	String oldName = "";
	for (Task task : this.tasksList) {
		System.out.println(task);
		if (task.getId() == oldId) {
			oldName = task.getName();
			System.out.println(oldName);
		}
	}
	System.out.println("Enter the new text of the task");
	String newName = scanner.nextLine().toUpperCase();
	System.out.println(newName);
	TaskRepository.updateName(newName, oldName);
	for (Task task : this.tasksList) {
		if (task.getName().equals(oldName)) {
			task.setName(newName);
		}
	}
	System.out.println("\n");
}
private void prioritizeTask() {
	displayTasks();
	System.out.println("Enter the ID of the task you want to update");
	int id = scanner.nextInt();
	scanner.nextLine();
	String name = "";
	for (Task task : this.tasksList) {
		System.out.println(task);
		if (task.getId() == id) {
			name = task.getName();
			System.out.println(name);
		}
	}
	System.out.println("Enter the new Priority of the task");
	String newPrio = scanner.nextLine().toUpperCase();
	TaskRepository.updatePriority(newPrio, name);
	for (Task task : this.tasksList) {
		if (task.getName().equals(name)) {
			task.setPriority(newPrio);
		}
	}
	System.out.println("\n");
}
private void markTaskCompletedId() {
	System.out.println("Task to Mark Completed?");
	int taskName = scanner.nextInt();
	TaskRepository.markCompletedId(taskName);
	for (Task task1 : this.tasksList) {
		if (task1.getId() == taskName) {
			task1.setCompleted(true);
		}
	}
	System.out.println("\n");
}
}