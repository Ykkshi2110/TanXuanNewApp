package com.peter.tanxuannewapp.service;

import com.peter.tanxuannewapp.domain.Permission;
import com.peter.tanxuannewapp.domain.Role;
import com.peter.tanxuannewapp.exception.ResourceAlreadyExistsException;
import com.peter.tanxuannewapp.repository.PermissionRepository;
import com.peter.tanxuannewapp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final ModelMapper modelMapper;
    private static final String ROLE_NAME_ALREADY_EXIST = "Role name already exist";

    public Role handleCreateRole(Role reqCreateRole) {
        if (this.roleRepository.existsByName(reqCreateRole.getName()))
            throw new ResourceAlreadyExistsException(ROLE_NAME_ALREADY_EXIST);

        // set permission
        if(reqCreateRole.getPermissions() != null){
            List<Integer> permissionIds = reqCreateRole.getPermissions().stream().map(Permission::getId).toList();
            List<Permission> permissions = this.permissionRepository.findByIdIn(permissionIds);
            reqCreateRole.setPermissions(permissions);
        }

        return this.roleRepository.save(reqCreateRole);
    }
}
