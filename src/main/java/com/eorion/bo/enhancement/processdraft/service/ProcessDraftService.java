package com.eorion.bo.enhancement.processdraft.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eorion.bo.enhancement.processdraft.adapter.outbound.ProcessDraftRepository;
import com.eorion.bo.enhancement.processdraft.domain.ProcessDraftStructureMapper;
import com.eorion.bo.enhancement.processdraft.domain.commom.PageInfo;
import com.eorion.bo.enhancement.processdraft.domain.dto.inbound.ProcessDraftQueryDTO;
import com.eorion.bo.enhancement.processdraft.domain.dto.inbound.ProcessDraftSaveDTO;
import com.eorion.bo.enhancement.processdraft.domain.dto.inbound.ProcessDraftUpdateDTO;
import com.eorion.bo.enhancement.processdraft.domain.dto.outbound.IdDTO;
import com.eorion.bo.enhancement.processdraft.domain.dto.outbound.ProcessDraftListDTO;
import com.eorion.bo.enhancement.processdraft.domain.entity.ProcessDraft;
import com.eorion.bo.enhancement.processdraft.domain.exception.DataNotExistException;
import com.eorion.bo.enhancement.processdraft.domain.exception.InsertFailedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProcessDraftService {

    private final ProcessDraftRepository repository;
    private final ProcessDraftStructureMapper structureMapper;

    public IdDTO<Integer> saveProcessDraft(ProcessDraftSaveDTO saveDTO) throws InsertFailedException {
        var applicationDraft = structureMapper.saveDtoToEntity(saveDTO);
        applicationDraft.setFormDraft(saveDTO.getFormDraft());
        try {
            repository.save(applicationDraft);
        } catch (Exception e) {
            log.error("save applicationDraft error: {}", e.getMessage());
            throw new InsertFailedException("保存失败！");
        }
        return new IdDTO<>(applicationDraft.getId());
    }

    public void updatedProcessDraft(Integer id, ProcessDraftUpdateDTO updateDTO) throws DataNotExistException {
        if (Objects.nonNull(repository.getById(id))) {
            var applicationDraft = structureMapper.updateDtoToEntity(updateDTO);
            applicationDraft.setFormDraft(updateDTO.getFormDraft());
            applicationDraft.setId(id);
            repository.updateById(applicationDraft);
        } else {
            throw new DataNotExistException("对应的资源不存在");
        }

    }

    public void deleteProcessDraft(Integer id) {
        repository.removeById(id);
    }

    public PageInfo<ProcessDraftListDTO> getProcessDraftPageList(Integer firstResult, Integer maxResults, ProcessDraftQueryDTO queryDTO) {
        LambdaQueryWrapper<ProcessDraft> queryWrapper = new LambdaQueryWrapper<>();
        Page<ProcessDraft> page = new Page<>(firstResult, maxResults);
        if (Objects.nonNull(queryDTO)) {
            queryWrapper.eq(StringUtils.isNotEmpty(queryDTO.getTenant()), ProcessDraft::getTenant, queryDTO.getTenant())
                    .eq(StringUtils.isNotEmpty(queryDTO.getUserId()), ProcessDraft::getUserId, queryDTO.getUserId())
                    .eq(Objects.nonNull(queryDTO.getType()), ProcessDraft::getType, queryDTO.getType())
                    .eq(Objects.nonNull(queryDTO.getResourceType()), ProcessDraft::getResourceType, queryDTO.getResourceType())
                    .eq(StringUtils.isNotEmpty(queryDTO.getProcessDefinitionKey()), ProcessDraft::getProcessDefinitionKey, queryDTO.getProcessDefinitionKey())
                    .like(StringUtils.isNotEmpty(queryDTO.getProcessDefinitionNameLike()), ProcessDraft::getProcessDefinitionName, queryDTO.getProcessDefinitionNameLike())
                    .like(StringUtils.isNotEmpty(queryDTO.getTaskDefinitionNameLike()), ProcessDraft::getTaskDefinitionName, queryDTO.getTaskDefinitionNameLike())
                    .like(StringUtils.isNotEmpty(queryDTO.getDraftNameLike()), ProcessDraft::getDraftName, queryDTO.getDraftNameLike())
                    .eq(StringUtils.isNotEmpty(queryDTO.getTaskDefinitionKey()), ProcessDraft::getTaskDefinitionKey, queryDTO.getTaskDefinitionKey())
                    .eq(StringUtils.isNotEmpty(queryDTO.getTaskInstanceId()), ProcessDraft::getTaskInstanceId, queryDTO.getTaskInstanceId());
            queryWrapper.and(StringUtils.isNotEmpty(queryDTO.getResourceNameLike()), wrapper -> wrapper
                    .like(ProcessDraft::getProcessDefinitionName, queryDTO.getResourceNameLike())
                    .or()
                    .like(ProcessDraft::getTaskDefinitionName, queryDTO.getResourceNameLike())
                    .or()
                    .like(ProcessDraft::getDraftName, queryDTO.getResourceNameLike()));
            if (StringUtils.isNotEmpty(queryDTO.getSort()) && "asc".equals(queryDTO.getSort())) {
                queryWrapper.orderByAsc(ProcessDraft::getCreatedTs);
            } else {
                queryWrapper.orderByDesc(ProcessDraft::getCreatedTs);
            }
        }
        var draftPage = repository.selectPageList(page, queryWrapper);
        return new PageInfo<>(draftPage.getTotal(), draftPage.getRecords().stream().map(structureMapper::entityToListDto).collect(Collectors.toList()));
    }

}
