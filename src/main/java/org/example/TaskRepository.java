package org.example;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

private static final Logger log = LoggerFactory.getLogger(TaskRepository.class);
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
		log.error("e: ", e);
	}
}

public static void updateName(String newTask, String oldTask) {
	try (Connection connection = getDataSource().getConnection()) {
		var updateCommand = "update TASK set name = ? where name = ?";
		var ps = connection.prepareStatement(updateCommand);
		ps.setString(1, newTask);
		ps.setString(2, oldTask);
		ps.execute();

	} catch (Exception e) {
		log.error("e: ", e);
	}
}

public static void markCompleted(String task) {
	try (Connection connection = getDataSource().getConnection()) {
		var updateCommand = "update TASK set completed = true where name = ?";
		var ps = connection.prepareStatement(updateCommand);
		ps.setString(1, task);

		ps.execute();

	} catch (Exception e) {
		log.error("e: ", e);
	}
}

public static void delete(String task) {
	try (Connection connection = getDataSource().getConnection()) {
		var deleteQuery = "delete from Task where name = ?";
		var ps = connection.prepareStatement(deleteQuery);
		ps.setString(1, task);
		ps.execute();
	} catch (Exception e) {
		log.error("e: ", e);
	}
}

public static void DeleteAll() {
	try (Connection connection = getDataSource().getConnection()) {
		var statement = connection.createStatement();
		var deleteQuery = "delete from Task";
		statement.execute(deleteQuery);
	} catch (Exception e) {
		log.error("e: ", e);
	}
}

public static List<Task> findQuery(String query) {
	try (Connection connection = getDataSource().getConnection()) {
		var statement = connection.createStatement();
		var resultSet = statement.executeQuery(query);
		var tasks = new ArrayList<Task>();
		while (resultSet.next())
		{
			var task = new Task(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getBoolean("completed"));
			tasks.add(task);
		}
		return tasks;
		} catch (Exception e) {
			log.error("e: ", e);
		}
		return null;
	}
}


