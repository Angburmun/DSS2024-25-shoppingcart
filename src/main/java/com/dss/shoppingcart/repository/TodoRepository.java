package com.dss.shoppingcart.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dss.shoppingcart.model.Todo;

@RepositoryRestResource(collectionResourceRel="tasks",path="tasks")
public interface TodoRepository extends JpaRepository<Todo, Long> {}
