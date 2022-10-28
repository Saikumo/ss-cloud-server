package org.saikumo.cloud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.saikumo.cloud.common.Constant;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
	private String fileName;

	@JsonFormat(pattern = Constant.secondFormatterPattern)
	private LocalDateTime lastModifiedTime;

	private Long fileSize;

	private String filePath;

	private Boolean isDirectory;
}
