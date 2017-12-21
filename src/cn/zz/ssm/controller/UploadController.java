package cn.zz.ssm.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.zz.ssm.utils.Commons;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;


@Controller
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping("uploadPic")
	public void uploadPic(HttpServletRequest request,String fileName,PrintWriter out){
		
		//从request获取文件流
		MultipartHttpServletRequest mh=(MultipartHttpServletRequest) request;
		CommonsMultipartFile cm=(CommonsMultipartFile) mh.getFile(fileName);
		byte[] fbytes=cm.getBytes();
		
		//避免文件名重复
		String newFileName="";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		newFileName=sdf.format(new Date());
		
		Random r=new Random();
		for(int i=0;i<3;i++){
			newFileName=newFileName+r.nextInt(10);
		}
		
		//获取文件扩展名
		String originalFilename=cm.getOriginalFilename();
		String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
		
		//创建jesy服务器，进行跨服务器上传
		Client client=Client.create();
		//把文件关联到远程服务器
		WebResource resource=client.resource(Commons.PICHOST+"/upload/"+newFileName+suffix);
		//上传
		resource.put(String.class,fbytes);
		
		
		//ajax回调
		//图片会显
		//数据库保存图片的相对路径
		String fullPath=Commons.PICHOST+"/upload/"+newFileName+suffix;
		String relativePath="/upload/"+newFileName+suffix;
		
		String result="{\"fullPath\":\""+fullPath+"\",\"relativePath\":\""+relativePath+"\"}";
		
		out.print(result);
	}
}
