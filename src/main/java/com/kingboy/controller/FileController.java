package com.kingboy.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.UUID;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/26 下午10:43
 * @desc 文件上传下载.
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString();
        String filePath = "/Users/kingboy/Desktop/" + fileName;
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return String.valueOf(fileName);
    }


    /**
     * 文件下载
     * @param fileName
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileName}")
    public void download(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try(ServletOutputStream outputStream = response.getOutputStream()){
            String filePath = "/Users/kingboy/Desktop/" + fileName;
            Files.copy(Paths.get(filePath), outputStream);
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment;filename=test.txt");
        }

    }

}
