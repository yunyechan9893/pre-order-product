package com.yechan.productserver.catalogue.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yechan.productserver.catalogue.service.CatalogueService;
import com.yechan.productserver.common.service.ResponseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({CatalogueController.class, ResponseService.class})
public class CatalogueControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CatalogueService catalogueService;

    @Nested
    @DisplayName("getCatalogues 메서드는")
    class GetCatalogues {

        @Test
        @DisplayName("Null 커서 아이디로 카탈로그 정보들을 가져올 수 있다")
        void successNullCursor() throws Exception {

            mockMvc.perform(get("/catalogues"))
                .andDo(print())
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("유효한 커서 아이디로 카탈로그 정보들을 가져올 수 있다")
        void successValidCursor() throws Exception {

            mockMvc.perform(get("/catalogues?cursorId=20"))
                .andDo(print())
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("getCatalogueDetail 메서드는")
    class GetCatalogueDetail {

        @Test
        @DisplayName("Null 커서 아이디로 카탈로그 정보들을 가져올 수 있다")
        void successNullCursor() throws Exception {

            mockMvc.perform(get("/catalogues/1"))
                .andDo(print())
                .andExpect(status().isOk());
        }
    }
}
