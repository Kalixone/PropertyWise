package com.propertywise.controller

import com.propertywise.dto.UserAreaRequestDto
import com.propertywise.dto.UserDto
import com.propertywise.dto.UserPriceRequestDto
import com.propertywise.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Managment", description = "Endpoints for user-related operations, including setting area and price thresholds for notifications.")
class UserController(private val userService: UserService) {

    @PutMapping("/area")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Set area threshold for notifications", description = "Allows the user to set a threshold for receiving property notifications based on area.")
    fun setArea(authentication: Authentication, @RequestBody userAreaRequestDto: UserAreaRequestDto): UserDto {
        return userService.areaThresholdForNotifications(authentication, userAreaRequestDto)
    }

    @PutMapping("/price")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Set price threshold for notifications", description = "Allows the user to set a threshold for receiving property notifications based on price.")
    fun setPrice(authentication: Authentication, @RequestBody userPriceRequestDto: UserPriceRequestDto): UserDto {
        return userService.priceThresholdForNotifications(authentication, userPriceRequestDto)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Delete a user", description = "Deletes a user by their ID. Only accessible by users with the 'ADMIN' role.")
    fun deleteUser(@PathVariable id: Long, authentication: Authentication) {
        return userService.deleteUser(id, authentication)
    }
}
