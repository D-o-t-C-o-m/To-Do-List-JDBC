package org.example;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UI {
private final ArrayList<Task> tasksList;
public UI() {
	tasksList = new ArrayList<>();
}

public void displayMenu() {
	try (Scanner scanner = new Scanner(System.in)){
	int choice = 0;

	while (choice != 8) {
		System.out.println("1. Create a task");
		System.out.println("2. Update a task");
		System.out.println("3. Mark a task as completed");
		System.out.println("4. Delete a task");
		System.out.println("5. Display all tasks");
		System.out.println("6. Display all tasks that are not completed");
		System.out.println("7. Delete all tasks");

		System.out.println("8. Exit");
		System.out.print("> ");
		choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {
			case 1:
				System.out.println("Enter the name of the task");
				String name = scanner.nextLine().toUpperCase();
				System.out.println("Enter the Priority of the task (High, Medium, Low)");
				String priority = scanner.nextLine().toUpperCase();
				while(!priority.equals("HIGH") && !priority.equals("MEDIUM") && !priority.equals("LOW")) {
					System.out.println("Priority must be High, Medium, or Low");
					priority = scanner.nextLine().toUpperCase();
					System.out.print("> ");
					System.out.println("\n");
					}
				boolean completed = false;
				Task task = new Task(0, name, completed, priority);
				TaskRepository.create(task);
				this.tasksList.add(task);
				break;
			case 2:
				System.out.println("Enter the name of the task you want to update");
				String oldName = scanner.nextLine().toUpperCase();
				System.out.println("Enter the new name of the task");
				String newName = scanner.nextLine().toUpperCase();
				TaskRepository.updateName(newName, oldName);
				for (Task task2 : this.tasksList) {
					if (task2.getName().equals(oldName)) {
						task2.setName(newName);
											}
				}
				System.out.println("\n");
				break;
			case 4:
				System.out.println("Enter the name of the task you want to delete");
				String deleteName = scanner.nextLine().toUpperCase();
				TaskRepository.delete(deleteName);
				this.tasksList.removeIf(task1 -> task1.getName().equals(deleteName));
				System.out.println("\n");
				break;
			case 5:
				System.out.println("All Tasks:");
				System.out.println(" ID | Name | Completed | Priority");
				Objects.requireNonNull(TaskRepository.findQuery("select * from TASK")).forEach(task1 -> System.out.println(task1.getId() + " | " + task1.getName() + " | " + task1.isCompleted()+ " | " + task1.getPriority()));
				System.out.println("\n");
				break;
			case 6:
				System.out.println(" ID | Name | Completed | Priority");
				System.out.println("All Tasks that are not completed:");
				var query = "select * from TASK where completed = false";
				Objects.requireNonNull(TaskRepository.findQuery(query)).forEach(task1 -> System.out.println(task1.getId() + " | " + task1.getName() + " | " + task1.isCompleted()+ " | " + task1.getPriority()));
				System.out.println("\n");
				break;
			case 7:
				System.out.println("Really delete all tasks? (y/n)");
				String answer = scanner.nextLine();
				if (answer.equals("y")) {
					TaskRepository.DeleteAll();
					this.tasksList.clear();
					System.out.println("Tasks deleted");
				}
				else {
					System.out.println("Tasks not deleted");
				}
				System.out.println("\n");
				break;
			case 3:
				System.out.println("Task to Mark Completed?");
				String taskName = scanner.nextLine().toUpperCase();
				TaskRepository.markCompleted(taskName);
				for (Task task1 : this.tasksList) {
					if (task1.getName().equals(taskName)) {
						task1.setCompleted(true);
					}
				}
				System.out.println("\n");
				break;
		}
	}
} catch (Exception e) {
		System.out.println("Entry must be a number");
	}
}}