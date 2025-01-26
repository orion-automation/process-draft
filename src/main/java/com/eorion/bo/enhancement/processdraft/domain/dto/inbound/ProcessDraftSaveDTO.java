package com.eorion.bo.enhancement.processdraft.domain.dto.inbound;

import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftResourceType;
import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.Map;

@Data
public class ProcessDraftSaveDTO {

    private String draftName;

    private String tenant;

    private ProcessDraftResourceType resourceType;

    private String userId;

    private String processDefinitionKey;

    private String processDefinitionName;

    private String taskDefinitionKey;

    private String taskDefinitionName;

    private String taskInstanceId;

    private ProcessDraftType type;

    private Map<String, Object> formDraft;

    public String getFormDraft() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (!this.formDraft.isEmpty()) {
                return objectMapper.writeValueAsString(this.formDraft);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
