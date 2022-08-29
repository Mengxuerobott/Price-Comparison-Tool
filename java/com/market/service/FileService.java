package com.market.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Elias on 2019/7/18
 */
public interface FileService {
    boolean uploadFile(String dir, String filename,  MultipartFile file) throws FileNotFoundException;
}
