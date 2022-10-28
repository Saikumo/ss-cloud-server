package org.saikumo.cloud.service;

import lombok.extern.slf4j.Slf4j;
import org.saikumo.cloud.common.ApiResult;
import org.saikumo.cloud.common.ApiStatus;
import org.saikumo.cloud.common.Constant;
import org.saikumo.cloud.dto.FileDto;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class FileService {

    public ApiResult<List<FileDto>> listFile(String filePath) {
        log.info("list file, filePath = {}", filePath);
        File file = new File(Constant.fileBasePath + filePath);

        File[] fileArray = file.listFiles();
        List<FileDto> fileDtoList = new ArrayList<>();

        if (!ObjectUtils.isEmpty(fileArray)) {
            for (File item : fileArray) {
                Instant instant = Instant.ofEpochMilli(item.lastModified());
                ZoneId zoneId = ZoneId.systemDefault();

                // build FileDto
                FileDto fileDto = FileDto.builder()
                        .fileName(item.getName())
                        .lastModifiedTime(LocalDateTime.ofInstant(instant, zoneId))
                        .fileSize(item.length())
                        .isDirectory(item.isDirectory())
                        .filePath(item.getPath().replace("\\","/")
                                .replace(Constant.fileBasePath,""))
                        .build();

                fileDtoList.add(fileDto);
            }
        }

        return ApiResult.success(fileDtoList);
    }

    public ApiResult<?> uploadFile(MultipartFile multipartFile, String filePath, Boolean overwriteFlag) {
        log.info("upload file, filePath = {}, fileName = {}", filePath, multipartFile.getOriginalFilename());
        File targetFile = new File(Constant.fileBasePath + filePath,
                Objects.requireNonNull(multipartFile.getOriginalFilename()));

        // whether overwrite
        if (targetFile.exists()) {
            // overwrite
            if (overwriteFlag) {
                log.info("overwrite file, filePath = {}, fileName = {}", filePath, multipartFile.getOriginalFilename());
                if (!targetFile.delete()) {
                    log.error("overwrite file failed, filePath = {}, fileName = {}", filePath,
                            multipartFile.getOriginalFilename());
                    return ApiResult.of(ApiStatus.DELETE_FILE_FAILURE);
                }
            } else {
                // not overwrite
                log.info("file exists and not overwrite, filePath = {}, fileName = {}", filePath,
                        multipartFile.getOriginalFilename());
                return ApiResult.of(ApiStatus.FILE_EXIST_AND_NOT_OVERWRITE);
            }
        }

        // save file
        try {
            multipartFile.transferTo(targetFile);
        } catch (Exception e) {
            log.error("save file failed, filePath = {}, fileName = {}", filePath,
                    multipartFile.getOriginalFilename());
            return ApiResult.failure(e.getMessage());
        }

        return ApiResult.success();
    }
}
