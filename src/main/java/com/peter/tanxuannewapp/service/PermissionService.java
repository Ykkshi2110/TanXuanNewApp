package com.peter.tanxuannewapp.service;

import com.peter.tanxuannewapp.domain.Permission;
import com.peter.tanxuannewapp.exception.ResourceAlreadyExistsException;
import com.peter.tanxuannewapp.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private static final String NAME_OR_ROUTE_EXISTS_EXCEPTION = "Name or Route already exists";

    public Permission handleCreatePermission(Permission reqCreatePermission) {
        if(this.permissionRepository.existsByNameAndRoute(reqCreatePermission.getName(), reqCreatePermission.getRoute())) throw new ResourceAlreadyExistsException(NAME_OR_ROUTE_EXISTS_EXCEPTION);
        return this.permissionRepository.save(reqCreatePermission);
    }
}
