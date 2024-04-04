package io.umid.taskapi.specs;

import io.umid.taskapi.entity.Task;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;

@Slf4j
public class TaskSpecs {


    public static Specification<Task> timestampBetween(Timestamp lowerBound, Timestamp upperBound, String property) {
        log.debug("Creating specification timestamp between: {}, {}, {}", lowerBound, upperBound, property);

        return (root, query, builder) -> {
            Predicate predicate = null;

            if (lowerBound != null) {
                predicate = builder.greaterThanOrEqualTo(root.get(property), lowerBound);
            }

            if (upperBound != null) {
                var upperBoundPredicate = builder.lessThanOrEqualTo(root.get(property), upperBound);
                predicate = builder.and(upperBoundPredicate, predicate);
            }

            if (predicate != null) {
                log.debug("Predicate expressions: {}", predicate.getExpressions());
            }

            return predicate;
        };
    }
}
