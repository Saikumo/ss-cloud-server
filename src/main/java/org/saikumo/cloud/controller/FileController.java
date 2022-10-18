package org.saikumo.cloud.controller;

import org.saikumo.cloud.ApiResult;
import org.saikumo.cloud.dto.FileDto;
import org.saikumo.cloud.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {

	@Autowired
	FileService fileService;

	@GetMapping("/")
	public ApiResult listFile(@RequestParam("filePath") String filePath) {
		return fileService.listFile(filePath);
	}



//	@PostMapping("/upload")
//	public ApiResult uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//		String filePath = "D:\\Programming\\Project\\ss-cloud-server\\src\\main\\resources\\static";
//		File targetFile = new File(filePath, file.getOriginalFilename());
//		// delete file if it exists
//		if (targetFile.exists()) {
//			targetFile.delete();
//		}
//
//		file.transferTo(targetFile);
//
//		return ApiResult.ok();
//	}



//	@PostMapping("/download")
//	public ApiResult downloadFile(HttpServletResponse response){
//		File file = new File("D:\\Programming\\Project\\ss-cloud-server\\src\\main\\resources\\static\\099b9f9a8fc914ba.jfif");
//		if(!file.exists()){
//			return "下载文件不存在";
//		}
//		response.reset();
//		response.setContentType("application/octet-stream");
//		response.setCharacterEncoding("utf-8");
//		response.setContentLength((int) file.length());
//		response.setHeader("Content-Disposition", "attachment;filename=" + fileName );
//
//		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
//			byte[] buff = new byte[1024];
//			OutputStream os  = response.getOutputStream();
//			int i = 0;
//			while ((i = bis.read(buff)) != -1) {
//				os.write(buff, 0, i);
//				os.flush();
//			}
//		} catch (IOException e) {
//			log.error("{}",e);
//			return "下载失败";
//		}
//		return "下载成功";
//	}
}
