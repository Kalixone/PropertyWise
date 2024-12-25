package com.propertywise.repository

import com.propertywise.model.Role
import com.propertywise.model.RoleName
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: RoleName): Role?
}
