package com.pipecode.kardexsales.controller.sale;

import com.pipecode.kardexsales.controller.sales.CreateSalesOperationEndPoint;
import com.pipecode.kardexsales.usecase.sales.CreateSales;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateSalesOperationEndPointTest {

    public static final String PATH = "/sales";
    private static final String EXCEPTION_MESSAGE_TEMPLATE = "No existe una tarjeta asociada a la cuenta %s";

    @Test
    void whenBodyContentValidStatus_thenReturn2xxOk() throws Exception {
        // Given
        final CreateSales createSales = mock(CreateSales.class);
        final var endpoint = new CreateSalesOperationEndPoint(createSales);
        final var mockMvc = MockMvcBuilders.standaloneSetup(endpoint).build();
        final String body = validBody();
        // When
        final var resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body));
        // Then
        resultActions.andExpect(status().is2xxSuccessful());
    }

    @Test
    void whenBodyMissing_thenReturnBadRequest() throws Exception {
        // Given
        final var endpoint = new CreateSalesOperationEndPoint(null);
        final var mockMvc = MockMvcBuilders.standaloneSetup(endpoint).build();
        // When
        final var resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON));
        // Then
        resultActions.andExpect(status().isBadRequest());
    }

    private String validBody() {
        return "{\n" +
                "  \"employeeLastName\": \"Villalobos\",\n" +
                "  \"employeeName\": \"Daniel\",\n" +
                "  \"identification\": \"1234\",\n" +
                "  \"product\": [\n" +
                "    {\n" +
                "      \"categoryName\": \"ron\",\n" +
                "      \"productName\": \"ron\",\n" +
                "      \"productQty\": 10\n" +
                "    }\n" +
                "  ]\n" +
                "}";

    }

}
