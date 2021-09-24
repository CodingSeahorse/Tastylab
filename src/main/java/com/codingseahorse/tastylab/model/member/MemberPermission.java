package com.codingseahorse.tastylab.model.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberPermission {
    TASTER_READ_AND_WRITE("taster:read_and_write"),
    BLOGGER_READ_AND_WRITE("blogger:read_and_write"),
    ADMIN_READ_AND_WRITE("admin:read_and_write");

    @Getter
    private final String permission;
}
