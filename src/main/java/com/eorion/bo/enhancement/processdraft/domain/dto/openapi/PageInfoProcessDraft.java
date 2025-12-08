package com.eorion.bo.enhancement.processdraft.domain.dto.openapi;

import com.eorion.bo.enhancement.processdraft.domain.commom.PageInfo;
import com.eorion.bo.enhancement.processdraft.domain.entity.ProcessDraft;

import java.util.List;

public class PageInfoProcessDraft extends PageInfo<ProcessDraft> {
    public PageInfoProcessDraft(Long total, List<ProcessDraft> data) {
        super(total, data);
    }
}
