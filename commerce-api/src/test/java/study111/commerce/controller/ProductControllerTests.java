package study111.commerce.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import study111.commerce.request.GetProductsRequest;
import study111.commerce.response.ProductsResponse;
import study111.commerce.service.ProductService;

import java.util.List;

import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("ProductController Tests")
@WebMvcTest(ProductController.class)
class ProductControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @WithMockUser
    @Test
    void getProducts() throws Exception {
        // 가상 시나리오 ?
        int size = 5;

        // TODO: mockito
        when(productService.getProducts(any(GetProductsRequest.class), any(Pageable.class)))
            .thenReturn(List.of(
                new ProductsResponse(),
                new ProductsResponse(),
                new ProductsResponse(),
                new ProductsResponse(),
                new ProductsResponse()
            ));

        // 실제 요청
        var result = mockMvc.perform(
            get("/products")
                .param("size", String.valueOf(size))
        ).andDo(print());

        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.data.length()").value(lessThanOrEqualTo(5)));
    }
}
