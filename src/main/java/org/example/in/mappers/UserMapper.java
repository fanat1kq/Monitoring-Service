package org.example.in.mappers;

import org.example.in.dto.UserDTO;
import org.example.model.User;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Mapping player entity to dto
     *
     * @param entity the player entity
     * @return mapped player dto
     */
    UserDTO toDto(User entity);

}