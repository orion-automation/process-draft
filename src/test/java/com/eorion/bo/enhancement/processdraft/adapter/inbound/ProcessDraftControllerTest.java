package com.eorion.bo.enhancement.processdraft.adapter.inbound;

import com.eorion.bo.enhancement.processdraft.adapter.outbound.ProcessDraftRepository;
import com.eorion.bo.enhancement.processdraft.domain.dto.inbound.ProcessDraftQueryDTO;
import com.eorion.bo.enhancement.processdraft.domain.entity.ProcessDraft;
import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftResourceType;
import com.eorion.bo.enhancement.processdraft.domain.enums.ProcessDraftType;
import com.eorion.bo.enhancement.processdraft.utils.BatchSQLExecutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.IdentityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProcessDraftControllerTest {
    private static final HttpHeaders headers = new HttpHeaders();

    static {
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString("demo:demo".getBytes(StandardCharsets.UTF_8)));
    }

    @Value("${spring.profiles.active:default}") // "default" if no profile is set
    private String activeProfile;

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private ProcessDraftRepository repository;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BatchSQLExecutor executor;

    private InputStreamReader getApplicationDraftDeleteReader() {
        if (activeProfile.equals("testcontainers")) {
            return new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("sql/delete-all.oracle.sql")));
        }
        return new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("sql/delete-all.sql")));
    }

    @BeforeEach
    public void clearUp() throws SQLException {
        executor.batchExecuteSqlFromFile(getApplicationDraftDeleteReader());
        identityService.setAuthenticatedUserId("demo");
    }

    @Test
    public void createProcessDraftReturn200() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/enhancement/draft")
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers)
                                .content("""
                                        {
                                        "tenant": "tenant",
                                        "resourceType": "1",
                                        "userId": "userId",
                                        "processDefinitionKey": "processDefinitionKey",
                                        "processDefinitionName": "processDefinitionName",
                                        "taskDefinitionKey": "ewrewr",
                                        "taskDefinitionName": null,
                                        "taskInstanceId": "ewrewr32ed23",
                                        "type": "1",
                                        "formDraft": {"key": "value"}
                                        }""")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void updateProcessDraftReturn204() throws Exception {

        ProcessDraft draft = new ProcessDraft();
        draft.setType(ProcessDraftType.DRAFT);
        draft.setFormDraft("sdsdfds");
        draft.setResourceType(ProcessDraftResourceType.PROCESS);
        draft.setTaskDefinitionKey("ewrewr");
        draft.setTenant("tenant");
        draft.setTaskInstanceId("ewrewr32ed23");
        draft.setUserId("userId");
        draft.setProcessDefinitionKey("processDefinitionKey");
        draft.setProcessDefinitionName("processDefinitionName");

        repository.save(draft);
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/enhancement/draft/{id}", draft.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers)
                                .content("""
                                        {
                                          "tenant": "tenant2",
                                          "resourceType": "2",
                                          "processDefinitionKey": "processDefinitionKey2",
                                          "processDefinitionName": "processDefinitionName2",
                                          "taskDefinitionKey": "ewrewrffffff",
                                          "taskDefinitionName": "taskDefinitionName2",
                                          "taskInstanceId": "ewrewr32ed23dddd",
                                          "type": "2",
                                        "formDraft": {"key": "value"}
                                        }""")
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void getProcessDraftListReturn204() throws Exception {

        ProcessDraft draft = new ProcessDraft();
        draft.setType(ProcessDraftType.DRAFT);
        draft.setFormDraft("{\"key\":\"value\"}");
        draft.setResourceType(ProcessDraftResourceType.PROCESS);
        draft.setTaskDefinitionKey("ewrewr");
        draft.setTenant("tenant");
        draft.setTaskInstanceId("ewrewr32ed23");
        draft.setUserId("userId");
        draft.setProcessDefinitionKey("processDefinitionKey");
        draft.setProcessDefinitionName("processDefinitionName");
        for (int i = 0; i < 5; i++) {
            draft.setId(null);
            repository.save(draft);
        }

        ProcessDraftQueryDTO queryDTO = new ProcessDraftQueryDTO();
        queryDTO.setUserId("userId");
        queryDTO.setTenant("tenant");
        queryDTO.setProcessDefinitionNameLike("processDefinitionName");
        queryDTO.setProcessDefinitionKey("processDefinitionKey");
        queryDTO.setResourceNameLike("process");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/enhancement/draft/list")
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers)
                                .param("firstResult", "0")
                                .param("maxResults", "2")
                                .content(mapper.writeValueAsString(queryDTO))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(2))
                .andDo(print());
    }


    @Test
    public void deleteProcessDraftReturn204() throws Exception {

        ProcessDraft draft = new ProcessDraft();
        draft.setType(ProcessDraftType.DRAFT);
        draft.setFormDraft("sdsdfds");
        draft.setResourceType(ProcessDraftResourceType.PROCESS);
        draft.setTaskDefinitionKey("ewrewr");
        draft.setTenant("tenant");
        draft.setTaskInstanceId("ewrewr32ed23");
        draft.setUserId("userId");
        draft.setProcessDefinitionKey("processDefinitionKey");
        draft.setProcessDefinitionName("processDefinitionName");
        repository.save(draft);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/enhancement/draft/{id}", draft.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers)
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
