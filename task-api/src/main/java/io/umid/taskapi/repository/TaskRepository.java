package io.umid.taskapi.repository;

import io.umid.taskapi.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {


    Page<Task> getAllByUserId(String userId, Pageable pageable);

}
