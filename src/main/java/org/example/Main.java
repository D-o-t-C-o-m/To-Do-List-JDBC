package org.example;

import java.util.List;

public class Main {
public static void main(String[] args) {
	Task task1 = new Task(55, "Learning Spring", false);
	TaskRepository.create(task1);
	TaskRepository.findById(task1);

	System.out.println("All Tasks:");
	System.out.println("=====================================");
	List<Task> taskList = TaskRepository.findAll();
	for (Task task : taskList) {
		System.out.println(task.getId() + " " + task.getName() + " " + task.isCompleted());
	}
	System.out.println("=====================================");
	Task task2 = new Task(1, "Learning Spring", true);
	TaskRepository.updateName(task2);
	TaskRepository.findById(task2);
	System.out.println("All Tasks:");
	System.out.println("=====================================");
	List<Task> taskList2 = TaskRepository.findAll();
	for (Task task : taskList2) {
		System.out.println(task.getId() + " " + task.getName() + " " + task.isCompleted());
	}
	System.out.println("=====================================");
	TaskRepository.displayNotCompleted();
	System.out.println("=====================================");
	TaskRepository.delete(task2);
	System.out.println("=====================================");
	TaskRepository.DeleteAll();


//Add a quick UI to interact with the TaskRepository, I guess we could also set priorities as it would just be a column in the DB?
}}