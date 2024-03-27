package org.example.in.mappers;

import org.example.in.dto.IndicationsDTO;
import org.example.in.dto.ReadingHistoryResponse;
import org.example.model.Indications;
import org.mapstruct.Mapper;


import java.util.List;

/**
 * Mapper for transaction entity
 */
@Mapper(componentModel = "spring")
public interface IndicationsMapper {

    /**
     * Mapping transactions list entity to dto list
     *
     * @param entities the transactions entities
     * @return mapped transaction dto list
     */
    List<IndicationsDTO> toDTOList(List<Indications> entities);

}