package com.example.esatto.mapper;

import com.example.esatto.dto.StreamerDto;
import com.example.esatto.entity.StreamerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StreamerMapper {

    StreamerDto entityToStreamerDto(StreamerEntity streamer);

    StreamerEntity streamerDtoToEntity(StreamerDto streamerDto);

}