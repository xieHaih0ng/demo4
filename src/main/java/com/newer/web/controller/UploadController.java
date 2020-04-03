package com.newer.web.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newer.domain.User;
import com.newer.dto.MsgDto;

@RestController
@RequestMapping("upload")
public class UploadController {
	
	@RequestMapping("upload")
	public String upload(MultipartFile  img,HttpSession session) throws Exception {
		String path="/upload/";
		if(img!=null && !"".equals(img.getOriginalFilename())) {
			//文件上传处理
			String oldName=img.getOriginalFilename();
			//得到源文件的扩展名
			String ext=oldName.substring(oldName.lastIndexOf("."));
			//得新的名称
			String newName=UUID.randomUUID()+ext;
			//自己增加，每天上传的文件，对应一个文件夹2020-2-21
			//实际应用中，要考虑各种情况，本次例子中只讲了最简单的情况
			
			//得到绝对路径
			String realpath= session.getServletContext().getRealPath("upload")+"/"+newName;
			//保存
			img.transferTo(new File(realpath));
			return path+newName;
		}
		return null;
	}

}
