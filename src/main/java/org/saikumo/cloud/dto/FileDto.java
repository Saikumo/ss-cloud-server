package org.saikumo.cloud.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FileDto {
	private String fileName;
	private LocalDateTime lastModifiedTime;
	private Long fileSize;
	private String filePath;
	private Boolean isDirectory;
}
