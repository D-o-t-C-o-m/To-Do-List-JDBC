package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {
public static void main(String[] args) throws SQLException {
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
	TaskRepository.update(task2);
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


	//try {
	//	var connection = DriverManager.getConnection("jdbc:h2:./todo;AUTO_SERVER=true", "sa", "");
//		var createTableSQL = "create table if not exists TASK (id identity primary key, name varchar, completed boolean)";
//		var statement = connection.createStatement();
//		statement.execute(createTableSQL);
//
//		var insertQuery = "insert into TASK (name) values ('Leaning!')";
//		statement.execute(insertQuery);
//
//		var insertStatement = "insert into Task (name) values (?)";
//		var preparedStatement = connection.prepareStatement(insertStatement);
//		preparedStatement.setString(1, "Learning Spring");
//		preparedStatement.execute();
//
//		var updateCommand = "update TASK set name = ? where name = 'Leaning!'";
//		preparedStatement = connection.prepareStatement(updateCommand);
//		preparedStatement.setString(1, "Learning Jakarta EE!");
//		preparedStatement.execute();
//
//		var deleteQuery = "delete from Task where name = 'Learning Jakarta EE!'";
//		statement.execute(deleteQuery);
//
//		var selectAllQuery = "Select * from TASK";
//		var resultSet = statement.executeQuery(selectAllQuery);
//		while (resultSet.next()) {
//			System.out.println("To Do Item: " + resultSet.getString("name"));
//
//

	//} catch (Exception e) {
//		System.out.println(e);
//	}

//	}
}}