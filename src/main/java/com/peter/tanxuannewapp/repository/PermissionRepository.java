package com.peter.tanxuannewapp.repository;

import com.peter.tanxuannewapp.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
