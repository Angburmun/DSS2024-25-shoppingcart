package com.dss.shoppingcart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.dss.shoppingcart.repositories.TodoRepository;


@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) { SpringApplication.run(Application.class, args); }

	@Bean
	CommandLineRunner jpaSample(TodoRepository todoRepo) {
	    return (args) -> {
	    	/*// Almacenar datos en la base de datos
	        todoRepo.save(new Todo("Test"));
	        Todo todo = new Todo("Test detallado");
	        todo.setDueDate(new Date());
	        todo.setDescription("Descripcion detallada");
	        todoRepo.save(todo);*/

	        // Log de los datos insertados
	        log.info("Todos encontrados con findAll():");
	        todoRepo.findAll().forEach(todos -> log.info(todos.toString())); 
	    };
	}
}
