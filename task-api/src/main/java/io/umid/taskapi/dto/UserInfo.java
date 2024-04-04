package io.umid.taskapi.dto;

public record UserInfo(
        String email,
        String username,
        String firstName,
        String lastName
) {
}
