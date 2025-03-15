package org.example;

import java.sql.Connection;

import static org.example.TaskRepository.getDataSource;

public class Main {
public static void main(String[] args) {
	try (Connection connection = getDataSource().getConnection()) {
		System.out.println("Yet another To-Do List");
		System.out.println("=======================\n");
		UI ui = new UI();
		ui.loadList();
		ui.displayMenu();

	} catch (Exception e) {
		System.out.println("Database connection failed");
	}
	//TODO: Add some safety to prevent crashes. Add a print function?
}
}