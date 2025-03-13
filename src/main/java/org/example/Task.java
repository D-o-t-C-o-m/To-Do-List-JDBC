package org.example;

public class Task {
private final int id;
private String name;
private boolean completed;
private String Priority;

public Task(int id, String name, boolean completed, String Priority) {
	this.id = id;
	this.name = name;
	this.completed = completed;
	this.Priority = Priority;
}

public int getId() {
	return id;
}

public String getPriority() {
	return Priority;
}
public void setPriority(String priority) {
	Priority = priority;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public boolean isCompleted() {
	return completed;
}

public void setCompleted(boolean completed) {
	this.completed = completed;
}
}


