package io.umid.taskapi.repository;

import io.umid.taskapi.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String>,
        JpaSpecificationExecutor<User> {

    @EntityGraph(attributePaths = User.Fields.tasks)
    @Override
    List<User> findAll(Specification<User> spec);

}
