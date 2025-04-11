package com.example.esatto.mapper;

import com.example.esatto.dto.StreamDto;
import com.example.esatto.entity.StreamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StreamMapper {

    StreamDto entityToStreamDto(StreamEntity stream);


}
