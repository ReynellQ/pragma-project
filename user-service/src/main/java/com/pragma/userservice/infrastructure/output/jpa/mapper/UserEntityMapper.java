package com.pragma.userservice.infrastructure.output.jpa.mapper;

import com.pragma.userservice.domain.model.User;
import com.pragma.userservice.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
/**
 * Mapping interface to map:
 *
 * - UserEntity to User and vice-versa
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserEntityMapper {
    UserEntity toEntity(User user);
    User toUser(UserEntity userEntity);

}
