package com.todoapp.todo;

import com.todoapp.todo.model.Task;
import com.todoapp.todo.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(TaskRepository repo) {
//		return args -> {
//			repo.save(new Task(null, "Learn Spring Boot", "Finish the tutorial", LocalDate.now().plusDays(2), false));
//			repo.save(new Task(null, "Practice SQL Joins", "Try join queries", LocalDate.now().plusDays(5), false));
//		};
//	}

}
