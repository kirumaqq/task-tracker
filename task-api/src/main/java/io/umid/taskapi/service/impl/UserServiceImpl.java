package io.umid.taskapi.service.impl;

import io.umid.taskapi.dto.TasksFilter;
import io.umid.taskapi.dto.UserTasksResponse;
import io.umid.taskapi.mapper.UserMapper;
import io.umid.taskapi.repository.UserRepository;
import io.umid.taskapi.service.UserService;
import io.umid.taskapi.specs.UserSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    @Override
    public List<UserTasksResponse> getAllUsersTasks(TasksFilter tasksFilter) {

        var completedAtSpec = UserSpecs.tasksCompletedAtBetween(tasksFilter.completedAtLowerBound(),
                tasksFilter.completedAtUpperBound());
        var statusSpec = UserSpecs.tasksStatus(tasksFilter.status());
        var spec = completedAtSpec.and(statusSpec);

        return userRepository.findAll(spec)
                .stream()
                .map(userMapper::toUserTasksResponse)
                .toList();
    }
}
