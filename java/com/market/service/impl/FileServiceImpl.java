package com.market.service.impl;

import com.market.service.FileService;
import com.market.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Elias on 2019/7/18
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {


    @Override
    public boolean uploadFile(String dir, String fileName, MultipartFile file)  {
        File uploadDir = new File(dir);
        if (!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        try {
            file.transferTo(new File(uploadDir.getPath(),fileName));
        } catch (IOException e) {
            log.error(fileName+"上传文件失败");
            return false;
        }
        return true;
    }
}
