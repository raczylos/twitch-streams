package com.example.esatto.mapper;

import com.example.esatto.dto.GameDto;
import com.example.esatto.entity.GameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameDto entityToGameDto(GameEntity game);

    GameEntity gameDtoToEntity(GameDto gameDto);

}
