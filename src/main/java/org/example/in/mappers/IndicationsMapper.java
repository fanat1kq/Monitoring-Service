package org.example.in.mappers;

import org.example.in.dto.IndicationsDTO;
import org.example.model.Indications;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface IndicationsMapper {


    IndicationsMapper INSTANCE = Mappers.getMapper(IndicationsMapper.class);

    Indications toEntity(IndicationsDTO dto);

    IndicationsDTO toDTO(Indications entity);


    List<IndicationsDTO> toDTOList(List<Indications> entities);

    List<Indications> toEntityList(List<IndicationsDTO> dtos);
}