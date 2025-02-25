package com.peter.tanxuannewapp.controller;

import com.peter.tanxuannewapp.domain.Permission;
import com.peter.tanxuannewapp.domain.annotation.ApiMessage;
import com.peter.tanxuannewapp.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping("/permissions/create")
    @ApiMessage("Create a permission")
    public ResponseEntity<Permission> createPermission(@RequestBody @Valid Permission reqCreatePermission) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(this.permissionService.handleCreatePermission(reqCreatePermission));
    }
}
