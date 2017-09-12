package com.airshiplay.play.main.rest;

import com.airlenet.plugin.storage.support.FileInfo;
import com.airlenet.plugin.storage.support.FileService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/center")
public class FileUploadController {

	@Autowired
	private FileService fileStorageService;

	// 图片扩展名
	private final static String[] fileTypes = new String[] { "gif", "jpg",
			"jpeg", "png", "bmp" };

	@RequestMapping(value = "/multipart/upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadImage(
			@RequestPart("file") MultipartFile part,
			@RequestParam(value = "dir", required = false) String dir) {
		if (part == null) {
			return error("请选择文件");
		}

		if (Strings.isNullOrEmpty(dir)) {
			dir = "image";
		}

		String url = null;

			url = fileStorageService.upload(part);
		 if(url==null){
		 	return error("file fail");
		 }
		return success(url);
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public Map<String, Object> listFile(@RequestParam("dir") String dir,
			@RequestParam("path") String path,
			@RequestParam("order") String order) {
		List<FileInfo> listFiles = fileStorageService.browser(dir, path);
		Map<String, Object> result = new HashMap<>();
		result.put("total_count", listFiles.size());
		result.put("file_list", listFiles);
		return result;
	}

	private Map<String, Object> error(String message) {
		Map<String, Object> result = new HashMap<>();
		result.put("error", 1);
		result.put("message", message);
		return result;
	}

	private Map<String, Object> success(String url) {
		Map<String, Object> result = new HashMap<>();
		result.put("success",true);
		result.put("msg", "操作成功");
		result.put("code", "success");
		result.put("urls", url);
		//{"success":true,"code":"success","msg":"操作成功","urls":"http://mdm.whenling.com/upload//file/53bf0731113241e7b9fe7305b8ad35dc.jpg"}

		return result;
	}

	public class NameComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename"))
						.compareTo((String) hashB.get("filename"));
			}
		}
	}

	public class SizeComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB
						.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB
						.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	public class TypeComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype"))
						.compareTo((String) hashB.get("filetype"));
			}
		}
	}

}
