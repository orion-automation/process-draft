package com.eorion.bo.enhancement.processdraft.adapter.inbound;

import com.eorion.bo.enhancement.processdraft.domain.commom.PageInfo;
import com.eorion.bo.enhancement.processdraft.domain.dto.inbound.ProcessDraftQueryDTO;
import com.eorion.bo.enhancement.processdraft.domain.dto.inbound.ProcessDraftSaveDTO;
import com.eorion.bo.enhancement.processdraft.domain.dto.inbound.ProcessDraftUpdateDTO;
import com.eorion.bo.enhancement.processdraft.domain.dto.outbound.IdDTO;
import com.eorion.bo.enhancement.processdraft.domain.exception.DataNotExistException;
import com.eorion.bo.enhancement.processdraft.domain.exception.InsertFailedException;
import com.eorion.bo.enhancement.processdraft.service.ProcessDraftService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enhancement/draft")
@AllArgsConstructor
public class ProcessDraftController {

    private final ProcessDraftService draftService;

    @PostMapping()
    public IdDTO<Integer> saveProcessDraft(@RequestBody ProcessDraftSaveDTO saveDTO) throws InsertFailedException {
        return draftService.saveProcessDraft(saveDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatedProcessDraft(@PathVariable Integer id, @RequestBody ProcessDraftUpdateDTO updateDTO) throws DataNotExistException {
        draftService.updatedProcessDraft(id, updateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProcessDraft(@PathVariable Integer id) {
        draftService.deleteProcessDraft(id);
    }

    @PostMapping("/list")
    public PageInfo<?> getProcessDraftPageList(@RequestParam(value = "firstResult", required = false, defaultValue = "0") Integer firstResult,
                                               @RequestParam(value = "maxResults", required = false, defaultValue = "2147483647") Integer maxResults,
                                               @RequestBody(required = false) ProcessDraftQueryDTO queryDTO) {
        return draftService.getProcessDraftPageList(firstResult, maxResults, queryDTO);
    }

}
