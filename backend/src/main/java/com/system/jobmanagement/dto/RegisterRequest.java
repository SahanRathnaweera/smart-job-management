package com.system.jobmanagement.dto;

import com.system.jobmanagement.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
