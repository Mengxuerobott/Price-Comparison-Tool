package com.market.controller.admin;

import com.market.exception.ResultException;
import com.market.service.FileService;
import com.market.service.SpuSourceService;
import com.market.utils.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Elias on 2019/7/18
 */
@Controller
@Api("文件控制器")
@Slf4j
@RequestMapping("/file")
public class FileController {
    private FileService fileService;
    private SpuSourceService spuSourceService;

    @Autowired
    public FileController(FileService fileService, SpuSourceService spuSourceService) {
        this.fileService = fileService;
        this.spuSourceService = spuSourceService;
    }

    @PostMapping("/imgUpload")
    @ApiOperation("图片上传")
    public String imgUpload(MultipartFile upFile, @RequestParam String gssId) throws IOException {
        String fileName = upFile.getOriginalFilename();
        String dirName = ResourceUtils.getURL("classpath:").getPath() + "static/assets/images/upload";
        fileName = Constant.FilePrefix.spuSourceImg + gssId + fileName.substring(fileName.lastIndexOf("."));
        System.out.println(fileName);
        if (fileService.uploadFile(dirName, fileName, upFile)) {
            return "redirect:/spuSource/getList_th";
        } else {
            return "error";
        }
    }

    @ApiOperation("下载")
    @GetMapping("/download")
    public void imgDownload(@RequestParam(required = false) String suffix, @RequestParam String gssId, HttpServletResponse response) {
        String dirName = "";
        try {
            dirName = ResourceUtils.getURL("classpath:").getPath() + "static/assets/images/upload";
        } catch (FileNotFoundException e) {
            log.error("文件未找到");
        }
        String fileName = Constant.FilePrefix.spuSourceImg + gssId + ".jpg";
        File file = new File(dirName , fileName);
        System.out.println(file.getAbsolutePath());
        if (!file.exists()){
            throw new ResultException("文件不存在");
        }
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] content = new byte[fis.available()];
            fis.read(content);
            fis.close();
            ServletOutputStream sos = response.getOutputStream();
            sos.write(content);
            sos.flush();
            sos.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            log.error("IO异常");
        }


    }

    @ApiOperation("文件上传页面")
    @GetMapping("/uploadHtml")
    public String getUploadHtml() {
        return "upload";
    }
}
