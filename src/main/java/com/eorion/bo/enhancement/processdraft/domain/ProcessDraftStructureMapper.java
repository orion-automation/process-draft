package com.eorion.bo.enhancement.processdraft.domain;

import com.eorion.bo.enhancement.processdraft.domain.dto.inbound.ProcessDraftSaveDTO;
import com.eorion.bo.enhancement.processdraft.domain.dto.inbound.ProcessDraftUpdateDTO;
import com.eorion.bo.enhancement.processdraft.domain.dto.outbound.ProcessDraftListDTO;
import com.eorion.bo.enhancement.processdraft.domain.entity.ProcessDraft;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProcessDraftStructureMapper {

    ProcessDraft saveDtoToEntity(ProcessDraftSaveDTO dto);

    ProcessDraft updateDtoToEntity(ProcessDraftUpdateDTO dto);

    ProcessDraftListDTO entityToListDto(ProcessDraft entity);
}
