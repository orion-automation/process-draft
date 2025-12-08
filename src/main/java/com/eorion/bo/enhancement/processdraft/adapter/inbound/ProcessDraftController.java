package com.eorion.bo.enhancement.processdraft.adapter.inbound;

import com.eorion.bo.enhancement.processdraft.domain.commom.ErrorMessage;
import com.eorion.bo.enhancement.processdraft.domain.commom.PageInfo;
import com.eorion.bo.enhancement.processdraft.domain.dto.inbound.ProcessDraftQueryDTO;
import com.eorion.bo.enhancement.processdraft.domain.dto.inbound.ProcessDraftSaveDTO;
import com.eorion.bo.enhancement.processdraft.domain.dto.inbound.ProcessDraftUpdateDTO;
import com.eorion.bo.enhancement.processdraft.domain.dto.openapi.PageInfoProcessDraft;
import com.eorion.bo.enhancement.processdraft.domain.dto.outbound.IdDTO;
import com.eorion.bo.enhancement.processdraft.domain.exception.DataNotExistException;
import com.eorion.bo.enhancement.processdraft.domain.exception.InsertFailedException;
import com.eorion.bo.enhancement.processdraft.service.ProcessDraftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enhancement/draft")
@Tag(name = "Process Draft Management")
public class ProcessDraftController {

    private final ProcessDraftService draftService;

    public ProcessDraftController(ProcessDraftService draftService) {
        this.draftService = draftService;
    }

    @Operation(summary = "Create process draft")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = IdDTO.class))),
            @ApiResponse(responseCode = "400", description = "Request body format validation failed",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "500", description = "Error when inserting",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class))),
    })
    @PostMapping
    public IdDTO<Integer> saveProcessDraft(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Process draft information to create", required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProcessDraftSaveDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "tenant": "tenant",
                                        "resourceType": "1",
                                        "userId": "userId",
                                        "processDefinitionKey": "processDefinitionKey",
                                        "processDefinitionName": "processDefinitionName",
                                        "taskDefinitionKey": "dummy",
                                        "taskDefinitionName": null,
                                        "taskInstanceId": "dummy32ed23",
                                        "type": "1",
                                        "formDraft": {"key": "value"}}""")))
            @RequestBody @Valid ProcessDraftSaveDTO saveDTO) throws InsertFailedException {
        return draftService.saveProcessDraft(saveDTO);
    }

    @Operation(summary = "Update process draft by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Request body format validation failed",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "404", description = "Process draft with given id not existed",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class))),
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatedProcessDraft(
            @Parameter(
                    description = "process draft id",
                    example = "1", required = true,
                    schema = @Schema(type = "integer", format = "int64")
            )
            @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Process draft information to update", required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProcessDraftUpdateDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                          "tenant": "tenant2",
                                          "resourceType": "2",
                                          "processDefinitionKey": "processDefinitionKey2",
                                          "processDefinitionName": "processDefinitionName2",
                                          "taskDefinitionKey": "dummy",
                                          "taskDefinitionName": "taskDefinitionName2",
                                          "taskInstanceId": "dummy32ed23dddd",
                                          "type": "2",
                                        "formDraft": {"key": "value"}
                                        }""")))
            @RequestBody @Valid ProcessDraftUpdateDTO updateDTO) throws DataNotExistException {
        draftService.updatedProcessDraft(id, updateDTO);
    }

    @Operation(summary = "Delete process draft by id")
    @ApiResponses(
            @ApiResponse(responseCode = "204", description = "Success")
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProcessDraft(
            @Parameter(
                    description = "process draft id",
                    example = "1", required = true,
                    schema = @Schema(type = "integer", format = "int64")
            )
            @PathVariable Integer id) {
        draftService.deleteProcessDraft(id);
    }

    @Operation(summary = "Get process draft list")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PageInfoProcessDraft.class))),
            @ApiResponse(responseCode = "400", description = "Request body format validation failed",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class))),
    })
    @PostMapping("/list")
    public PageInfo<?> getProcessDraftPageList(
            @Parameter(
                    description = "Zero-based page index (0..N)",
                    example = "0",
                    schema = @Schema(type = "integer", format = "int32", defaultValue = "0")
            )
            @RequestParam(value = "firstResult", required = false, defaultValue = "0") Integer firstResult,
            @Parameter(
                    description = "The size of the page to be returned",
                    example = "10",
                    schema = @Schema(type = "integer", format = "int32", defaultValue = "2147483647")
            )
            @RequestParam(value = "maxResults", required = false, defaultValue = "2147483647") Integer maxResults,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Query conditions of process draft information",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProcessDraftQueryDTO.class)))
            @RequestBody(required = false) @Valid ProcessDraftQueryDTO queryDTO) {
        return draftService.getProcessDraftPageList(firstResult, maxResults, queryDTO);
    }

}
