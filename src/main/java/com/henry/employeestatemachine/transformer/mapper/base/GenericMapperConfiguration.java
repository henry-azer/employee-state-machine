package com.henry.employeestatemachine.transformer.mapper.base;

import com.henry.employeestatemachine.dto.base.BaseDto;
import com.henry.employeestatemachine.model.base.BaseEntity;
import org.mapstruct.*;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface GenericMapperConfiguration {

    @Mappings({
            @Mapping(target = "creationDate", source = "createdDate"),
            @Mapping(target = "lastModificationDate", source = "modifiedDate")
    })
    BaseDto anyEntityToDto(BaseEntity entity);

    @Mappings({
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
    })
    BaseEntity anyDtoToEntity(BaseDto dto);
}