package io.umid.taskapi.repository;

import io.umid.taskapi.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long>, JpaSpecificationExecutor<Task> {


    Page<Task> getAllByUserId(String userId, Pageable pageable);

    @EntityGraph(attributePaths = "user")
    @Override
    List<Task> findAll(Specification<Task> spec);
}
