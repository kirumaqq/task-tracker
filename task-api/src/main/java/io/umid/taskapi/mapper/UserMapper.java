package io.umid.taskapi.mapper;

import io.umid.taskapi.dto.UserTasksResponse;
import io.umid.taskapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = TaskMapper.class)
public interface UserMapper {


    UserTasksResponse toUserTasksResponse(User user);

}
