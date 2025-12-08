package com.eorion.bo.enhancement.processdraft.domain.dto.inbound;

import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftResourceType;
import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftType;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProcessDraftQueryDTO {
    @Size(max = 64)
    private String userId;
    @Size(max = 255)
    private String tenant;

    private ProcessDraftResourceType resourceType;
    @Size(max = 255)
    private String processDefinitionKey;
    @Size(max = 255)
    private String processDefinitionNameLike;
    @Size(max = 255)
    private String taskDefinitionKey;
    @Size(max = 255)
    private String taskDefinitionNameLike;
    @Size(max = 255)
    private String draftNameLike;
    @Size(max = 255)
    private String taskInstanceId;
    @Size(max = 255)
    private String resourceNameLike;

    private ProcessDraftType type;
    @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String sort;

}
