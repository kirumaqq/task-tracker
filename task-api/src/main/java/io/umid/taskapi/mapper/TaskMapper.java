package io.umid.taskapi.mapper;

import io.umid.taskapi.dto.CreateTask;
import io.umid.taskapi.dto.EditTask;
import io.umid.taskapi.dto.TaskResponse;
import io.umid.taskapi.dto.TaskUserResponse;
import io.umid.taskapi.entity.Task;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = UserMapper.class)
public interface TaskMapper {

    TaskResponse toResponse(Task task);


    Task fromCreateTask(CreateTask createTask);


    void updateTask(EditTask editTask, @MappingTarget Task task);


    @Mapping(target = "userInfo", source = "user")
    TaskUserResponse toTaskUserResponse(Task task);

}
