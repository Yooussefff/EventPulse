package com.EventPulse.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private String userName;
}
