package com.eorion.bo.enhancement.processdraft.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftResourceType;
import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftType;
import lombok.Data;

@Data
@TableName(value = "ENHANCEMENT_PROCESS_DRAFT")
public class ProcessDraft {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "DRAFT_NAME")
    private String draftName;

    @TableField(value = "TENANT_TXT")
    private String tenant;

    @TableField(value = "RESOURCE_TYPE")
    private ProcessDraftResourceType resourceType;

    @TableField(value = "USER_ID")
    private String userId;

    @TableField(value = "PROCESS_DEFINITION_KEY")
    private String processDefinitionKey;

    @TableField(value = "PROCESS_DEFINITION_NAME")
    private String processDefinitionName;

    @TableField(value = "TASK_DEFINITION_KEY")
    private String taskDefinitionKey;

    @TableField(value = "TASK_DEFINITION_NAME")
    private String taskDefinitionName;

    @TableField(value = "TASK_INSTANCE_ID")
    private String taskInstanceId;

    @TableField(value = "TYPE")
    private ProcessDraftType type;

    @TableField(value = "FORM_DRAFT")
    private String formDraft;

    @TableField(value = "CREATED_TS", fill = FieldFill.INSERT)
    private Long createdTs;

    @TableField(value = "UPDATED_TS", fill = FieldFill.INSERT_UPDATE)
    private Long updatedTs;

    @TableField(value = "CREATE_BY_TXT", fill = FieldFill.INSERT)
    private String createdBy;

    @TableField(value = "UPDATED_BY_TXT", fill = FieldFill.UPDATE)
    private String updatedBy;

}
