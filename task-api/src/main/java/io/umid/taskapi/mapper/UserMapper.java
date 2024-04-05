package io.umid.taskapi.mapper;

import io.umid.taskapi.dto.UserInfo;
import io.umid.taskapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {


    UserInfo toUserInfo(User user);
}
