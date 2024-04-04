package io.umid.taskapi.mapper;

import io.umid.taskapi.dto.CreateTask;
import io.umid.taskapi.dto.EditTask;
import io.umid.taskapi.dto.TaskResponse;
import io.umid.taskapi.entity.Task;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {

    TaskResponse toResponse(Task task);


    Task fromCreateTask(CreateTask createTask);


    void updateTask(EditTask editTask, @MappingTarget Task task);

}
