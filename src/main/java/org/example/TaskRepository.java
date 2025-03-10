package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

public TaskRepository() {
}

private static DataSource getDataSource() {
	var dataSource = new HikariDataSource();
	dataSource.setJdbcUrl("jdbc:h2:./todo;AUTO_SERVER=true");
	dataSource.setUsername("sa");
	dataSource.setPassword("");
	return dataSource;
}

public static void create(Task task) {
	try (Connection connection = getDataSource().getConnection()) {
		String insertStatement = "insert into TASK (name, completed) values (?, ?)";
		var ps = connection.prepareStatement(insertStatement);
		ps.setString(1, task.getName());
		ps.setBoolean(2, task.isCompleted());
		ps.execute();
	} catch (Exception e) {
		System.out.println(e);
	}
}

public static void updateName(Task task) {
	try (Connection connection = getDataSource().getConnection()) {
		var updateCommand = "update TASK set name = ?, completed = ? where id = ?";
		var ps = connection.prepareStatement(updateCommand);
		ps.setString(1, task.getName());
		ps.setBoolean(2, task.isCompleted());
		ps.setInt(3, task.getId());

		ps.execute();

	} catch (Exception e) {
		System.out.println(e);
	}
}

public static void delete(Task task) {
	try (Connection connection = getDataSource().getConnection()) {
			var statement = connection.createStatement();
			var deleteQuery = "delete from Task where name = ?";
			statement.execute(deleteQuery);

	} catch (Exception e) {
		System.out.println(e);
	}
}

public static void findById(Task task) {
	try (Connection connection = getDataSource().getConnection()) {
		var selectQuery = "Select * from Task where name = ?";
		var ps = connection.prepareStatement(selectQuery);
		ps.setString(1, task.getName());
		System.out.println(task.getId() + " " + task.getName() + " " + task.isCompleted());
	} catch (Exception e) {
		System.out.println(e);
	}
}

public static List<Task> findAll() {
	try (Connection connection = getDataSource().getConnection()) {
		var selectAllQuery = "Select * from TASK";
		var statement = connection.createStatement();
		var resultSet = statement.executeQuery(selectAllQuery);
		var tasks = new ArrayList<Task>();
		while (resultSet.next()) {
			var task = new Task(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getBoolean("completed"));
			tasks.add(task);
		}
		return tasks;
	} catch (Exception e) {
		System.out.println(e);
	}
	return null;
}

public static void DeleteAll() {
	try (Connection connection = getDataSource().getConnection()) {
		var statement = connection.createStatement();
		var deleteQuery = "delete from Task";
		statement.execute(deleteQuery);
	} catch (Exception e) {
		System.out.println(e);
	}
}

public static void displayNotCompleted() {
	System.out.println("The following are not completed:");
	try (Connection connection = getDataSource().getConnection()) {
		var selectAllQuery = "Select * from TASK where completed = false";
		var statement = connection.createStatement();
		var resultSet = statement.executeQuery(selectAllQuery);
		var tasks = new ArrayList<Task>();
		while (resultSet.next()) {
			var task = new Task(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getBoolean("completed"));
			tasks.add(task);
		}
	} catch (Exception e) {
		System.out.println(e);
	}
}
}
