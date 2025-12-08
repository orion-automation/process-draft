package com.eorion.bo.enhancement.processdraft.config;

import com.eorion.bo.enhancement.processdraft.domain.commom.ErrorMessage;
import com.eorion.bo.enhancement.processdraft.domain.exception.DataNotExistException;
import com.eorion.bo.enhancement.processdraft.domain.exception.InsertFailedException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ProcessDraftRestControllerAdvice {
    /**
     * InsertFailedException handler
     *
     * @param e
     * @return
     */
    @ExceptionHandler(InsertFailedException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "500",
                    description = "insert failed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    })
    @ResponseBody
    public ErrorMessage handleInsertFailedException(InsertFailedException e) {
        log.error(e.getMessage());
        return new ErrorMessage("插入数据错误");
    }

    /**
     * 未找到资源
     */
    @ExceptionHandler(DataNotExistException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "404",
                    description = "data not exist",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    })
    @ResponseBody
    public ErrorMessage notFound(DataNotExistException e) {
        log.error(e.getLocalizedMessage());
        return new ErrorMessage("请求资源不存在");
    }
}
