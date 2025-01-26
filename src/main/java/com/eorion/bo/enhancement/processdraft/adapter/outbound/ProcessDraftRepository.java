package com.eorion.bo.enhancement.processdraft.adapter.outbound;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eorion.bo.enhancement.processdraft.domain.entity.ProcessDraft;
import com.eorion.bo.enhancement.processdraft.mapper.ProcessDraftMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ProcessDraftRepository extends ServiceImpl<ProcessDraftMapper, ProcessDraft> {

    private final ProcessDraftMapper mapper;

    public Page<ProcessDraft> selectPageList(Page<ProcessDraft> page, LambdaQueryWrapper<ProcessDraft> queryWrapper) {
        return mapper.selectPage(page, queryWrapper);
    }
}
