package com.kingboy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KingboySpringbootFileApplicationTests {

    @Resource
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 测试文件上传
     * @throws Exception
     */
    @Test
    public void uploadFileWhenSuccess() throws Exception {
        String fileName = mockMvc.perform(fileUpload("/file")
                .file(new MockMultipartFile("file", "test.txt", MediaType.MULTIPART_FORM_DATA_VALUE, "Hello World!".getBytes())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(fileName);
    }



}
