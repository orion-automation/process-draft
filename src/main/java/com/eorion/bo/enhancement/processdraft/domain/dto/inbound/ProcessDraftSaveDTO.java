package com.eorion.bo.enhancement.processdraft.domain.dto.inbound;

import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftResourceType;
import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Map;

@Data
public class ProcessDraftSaveDTO {
    @Size(max = 255)
    private String draftName;
    @Size(max = 64)
    private String tenant;

    private ProcessDraftResourceType resourceType;
    @Size(max = 64)
    private String userId;
    @Size(max = 255)
    private String processDefinitionKey;
    @Size(max = 255)
    private String processDefinitionName;
    @Size(max = 255)
    private String taskDefinitionKey;
    @Size(max = 255)
    private String taskDefinitionName;
    @Size(max = 255)
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
