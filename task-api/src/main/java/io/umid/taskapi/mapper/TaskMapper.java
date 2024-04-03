package io.umid.taskapi.mapper;

import io.umid.taskapi.dto.TaskResponse;
import io.umid.taskapi.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    TaskResponse toResponse(Task task);
}
