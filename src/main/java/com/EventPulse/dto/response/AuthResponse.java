package com.EventPulse.dto.response;

import com.EventPulse.entities.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {
    private String token;
    private String email;
    private String name;
    private String userName;
    private Role role;
}
