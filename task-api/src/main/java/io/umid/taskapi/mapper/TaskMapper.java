package io.umid.taskapi.mapper;

import io.umid.taskapi.dto.CreateTask;
import io.umid.taskapi.dto.TaskResponse;
import io.umid.taskapi.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    TaskResponse toResponse(Task task);


    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    Task fromCreateTask(CreateTask createTask);
}
