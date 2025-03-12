package org.example;

import java.util.Scanner;

public class UI {

public UI() {


}

public void displayMenu() {
	Scanner scanner = new Scanner(System.in);
	int choice = 0;

	while (choice != 8) {
	System.out.println("1. Create a task");
	System.out.println("2. Update a task");
	System.out.println("3. Delete a task");
	System.out.println("4. Display all tasks");
	System.out.println("5. Display all tasks that are not completed");
	System.out.println("6. Delete all tasks");
	System.out.println("7. Mark a task as completed");
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
				boolean completed = false;
				Task task = new Task(0, name, completed);
				TaskRepository.create(task);
				break;
			case 2:
				System.out.println("Enter the name of the task you want to update");
				String oldName = scanner.nextLine().toUpperCase();
				System.out.println("Enter the new name of the task");
				String newName = scanner.nextLine().toUpperCase();
				TaskRepository.updateName(newName, oldName);
				break;
			case 3:
				System.out.println("Enter the name of the task you want to delete");
				String deleteName = scanner.nextLine().toUpperCase();
				TaskRepository.delete(deleteName);
				break;
			case 4:
				System.out.println("All Tasks:");
				System.out.println(" ID | Name | Completed");
				TaskRepository.findQuery("select * from TASK").forEach(task1 -> System.out.println(task1.getId() + " " + task1.getName() + " " + task1.isCompleted()));
				break;
			case 5:
				System.out.println(" ID | Name | Completed");
				System.out.println("All Tasks that are not completed:");
				var query = "select * from TASK where completed = false";
				TaskRepository.findQuery(query).forEach(task1 -> System.out.println(task1.getId() + " " + task1.getName() + " " + task1.isCompleted()));
				break;
			case 6:
				TaskRepository.DeleteAll();
				break;
			case 7:
				System.out.println("Task to Mark Completed?");
				String taskName = scanner.nextLine().toUpperCase();
				TaskRepository.markCompleted(taskName);
				break;
		}
	}
}}