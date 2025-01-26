package com.eorion.bo.enhancement.processdraft.domain.dto.outbound;

import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftResourceType;
import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class ProcessDraftListDTO {

    private Integer id;

    private String draftName;

    private String tenant;

    private ProcessDraftResourceType resourceType;

    private String processDefinitionKey;

    private String processDefinitionName;

    private String taskDefinitionKey;

    private String taskDefinitionName;

    private String taskInstanceId;

    private ProcessDraftType type;

    private String formDraft;

    private Long createdTs;

    private Long updatedTs;

    private String createdBy;

    private String updatedBy;

    public Object getFormDraft() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (StringUtils.isNotEmpty(this.formDraft)) {
                return objectMapper.readValue(formDraft, Object.class);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return this.formDraft;
    }
}
