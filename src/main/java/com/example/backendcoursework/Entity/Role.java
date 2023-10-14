package com.example.backendcoursework.Entity;

import com.example.backendcoursework.Roles.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.backendcoursework.Roles.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER,ADMIN
}