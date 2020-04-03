package com.newer.web.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.newer.domain.User;
import com.newer.dto.MsgDto;
import com.newer.dto.UserDto;
import com.newer.service.UserService;

//@CrossOrigin //支持跨域（局部）
@RestController  //这个类的所有方法，都是异步处理
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("findById") 
	public User findById(Integer id) {
		return this.userService.findById(id);
	}
	
	//@RequestBody,以json格式接收参数，对应客户端的请求：application/json
	@RequestMapping("findById2") 
	public User findById2(@RequestBody UserDto userDto ) {
		return this.userService.findById(userDto.getId());
	}
	
	 
	
	//分页查询：每页显示条数pageSize，当前页page
	@RequestMapping("findPages")
	public PageInfo fingPages(UserDto userDto) {
		PageInfo pageInfo=this.userService.findPages(userDto);
		return pageInfo;
	}
	
	@RequestMapping("save")
	public MsgDto save(User user,MultipartFile  img,HttpSession session) throws Exception {
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
			
			//将路径保存到user对象中
			user.setPhoto(newName);
		}
		
		this.userService.save(user);
		return new MsgDto(true, "保存成功");
	}
	
	//不需要文件上传
	@RequestMapping("save2")
	public MsgDto save2(@RequestBody User user) throws Exception {
		this.userService.save(user);
		return new MsgDto(true, "保存成功");
	}
	
	
	
	//不需要文件上传
	@RequestMapping("delete")
	public MsgDto delete(Integer id) throws Exception {
		this.userService.delete(id);
		return new MsgDto(true, "删除成功");
	}
	
	
	/**
	 * 异步处理
	 * @RestController ==  @Controller +@ResponseBody
	 * @param uname
	 * 返回：String ，对象，Map
	 *    标志 true，false
	 *    提示信息：mesage
	 * 
	 */
	@RequestMapping("exists")
	@ResponseBody
	public MsgDto  exists(String uname) {
		boolean tag=this.userService.isExists(uname);
		if(tag) {
			return new MsgDto(true,uname+",已经存在");
		}else {
			return new MsgDto(false,uname+",可用");
		}
	}

	//---------------------------
	@RequestMapping("findUsers")
	public List<User> findUsers(UserDto userDto) {
	 List list= this.userService.findUsers(userDto);
	 return list;
	}
	
	
	@RequestMapping("update")
	public MsgDto update(User user,MultipartFile  img,HttpSession session) throws Exception {
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
			
			//将路径保存到user对象中
			user.setPhoto(newName);
		}
		
		this.userService.update(user);
		return new MsgDto(true, "保存成功");
	}
	
	
}
