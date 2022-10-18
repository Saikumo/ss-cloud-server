package org.saikumo.cloud.service;

import lombok.extern.slf4j.Slf4j;
import org.saikumo.cloud.ApiResult;
import org.saikumo.cloud.common.Constant;
import org.saikumo.cloud.dto.FileDto;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FileService {

	public ApiResult listFile(String filePath) {
		log.info("list file, filePath = {}",filePath);
		File file = new File(Constant.fileBasePath + filePath);

		File[] fileArray = file.listFiles();
		List<FileDto> fileDtoList = new ArrayList<>();

		if (!ObjectUtils.isEmpty(fileArray)) {
			for (File item : fileArray) {
				Instant instant = Instant.ofEpochMilli(item.lastModified());
				ZoneId zoneId = ZoneId.systemDefault();

				FileDto fileDto = new FileDto();
				fileDto.setFileName(item.getName());
				fileDto.setLastModifiedTime(LocalDateTime.ofInstant(instant, zoneId));
				fileDto.setFileSize(item.length());
				fileDto.setIsDirectory(item.isDirectory());
				fileDto.setFilePath(item.getPath().replace("\\","/")
						.replace(Constant.fileBasePath,""));

				fileDtoList.add(fileDto);
			}
		}

		return ApiResult.success(fileDtoList);
	}
}
