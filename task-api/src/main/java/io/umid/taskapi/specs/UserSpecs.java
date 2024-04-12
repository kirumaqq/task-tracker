package io.umid.taskapi.specs;


import io.umid.taskapi.entity.Task;
import io.umid.taskapi.entity.TaskStatus;
import io.umid.taskapi.entity.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;

@Slf4j
public class UserSpecs {


    public static Specification<User> tasksCompletedAtBetween(Timestamp lowerBound, Timestamp upperBound) {

        return (root, query, cb) -> {
            log.debug("Building completedAt spec");
            log.debug("Lower bound and upper bound: {}, {}", lowerBound, upperBound);

            Predicate predicate = null;

            if (lowerBound != null || upperBound != null) {

                Join<Object, Object> tasksJoin = root.join(User.Fields.tasks);

                if (lowerBound != null) {
                    predicate = cb.greaterThan(tasksJoin.get(Task.Fields.completedAt), lowerBound);
                }

                if (upperBound != null) {
                    var upperBoundPredicate = cb.lessThan(tasksJoin.get(Task.Fields.completedAt), upperBound);
                    predicate = cb.and(predicate, upperBoundPredicate);
                }
            }

            log.debug("Predicate: {}", predicate);

            return predicate;
        };
    }

    public static Specification<User> tasksStatus(TaskStatus status) {

        return (root, query, cb) -> {
            log.debug("Building tasks status specification: {}", status);

            Predicate predicate = null;

            if (status != null) {
                Join<Object, Object> tasksJoin = root.join(User.Fields.tasks);
                predicate = cb.equal(tasksJoin.get(Task.Fields.status), status);
            }

            log.debug("Predicate: {}", predicate);

            return predicate;
        };
    }

}
