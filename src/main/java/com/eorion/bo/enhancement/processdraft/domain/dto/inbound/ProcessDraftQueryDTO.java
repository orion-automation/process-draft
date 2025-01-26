package com.eorion.bo.enhancement.processdraft.domain.dto.inbound;

import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftResourceType;
import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftType;
import lombok.Data;

@Data
public class ProcessDraftQueryDTO {
    private String userId;

    private String tenant;

    private ProcessDraftResourceType resourceType;

    private String processDefinitionKey;

    private String processDefinitionNameLike;

    private String taskDefinitionKey;

    private String taskDefinitionNameLike;

    private String draftNameLike;

    private String taskInstanceId;

    private String resourceNameLike;

    private ProcessDraftType type;

    private String sort;

}
