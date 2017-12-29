### 环境设置
```
server:
  port: 8080
spring:
  application:
    name: kingboy-springboot-file
  http:
    multipart:
      #设置单个文件的大小限制
      max-file-size: 100MB
      #设置单次请求的总大小限制
      max-request-size: 1000MB
```


### 文件上传接口

@RequestParam("file")中的"file"为前台对应的name属性。

```java
@PostMapping
public String upload(@RequestParam("file") MultipartFile file) throws IOException {
    //获取文件的大小，以size为准
    long size = file.getSize();
    int available = file.getInputStream().available();
    int length = file.getBytes().length;
    //获取参数名，也就是"file"
    String name = file.getName();
    //获取文件原本的名称
    String originName = file.getOriginalFilename();
    
    //保存文件
    String fileName = UUID.randomUUID().toString();
    String filePath = "/Users/kingboy/Desktop/" + fileName;
    Files.copy(file.getInputStream(), Paths.get(filePath));
    return String.valueOf(fileName);
}
```

### 文件下载

```java
    @GetMapping("/{fileName}")
    public void download(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try(ServletOutputStream outputStream = response.getOutputStream()){
            String filePath = "/Users/kingboy/Desktop/" + fileName;
            Files.copy(Paths.get(filePath), outputStream);
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment;filename=test.txt");
        }
    }
```