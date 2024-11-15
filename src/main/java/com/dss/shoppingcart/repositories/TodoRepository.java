package com.dss.shoppingcart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dss.shoppingcart.models.Todo;

@RepositoryRestResource(collectionResourceRel="tasks",path="tasks")
public interface TodoRepository extends JpaRepository<Todo, Long> {}
