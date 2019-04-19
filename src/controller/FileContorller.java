package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 本类用于在SpringMVC中上传文件<p>
 * 【注】使用MappingJackson2JsonView()需要导包：jackson-databind-xxx.jar
 */
@Controller
@RequestMapping("/file")
public class FileContorller {
	
	/**
	 * 当使用HttpServletRequest作为方法参数的时候，会造成API侵入
	 * @param request
	 * @return
	 */
	@RequestMapping("/upload")
	public ModelAndView upload(HttpServletRequest request) {
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest)request;
		MultipartFile multipartFile = mhsr.getFile("file");
		ModelAndView mv = new ModelAndView();
		mv.setView(new MappingJackson2JsonView());
		String fileName = multipartFile.getOriginalFilename();
		File dest = new File(fileName);
		try {
			multipartFile.transferTo(dest);
			mv.addObject("success", true);
			mv.addObject("msg", "上传文件成功");
		}catch(IllegalStateException | IOException e) {
			mv.addObject("success", false);
			mv.addObject("msg", "上传文件失败");
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * MultipartFile是由Spring MVC提供的类
	 * @param multipartFile
	 * @return
	 */
	@RequestMapping("/uploadMultipartFile")
	public ModelAndView uploadMultipartFile(MultipartFile multipartFile) {
		ModelAndView mv = new ModelAndView();
		mv.setView(new MappingJackson2JsonView());
		String fileName = multipartFile.getOriginalFilename();
		multipartFile.getContentType();
		File dest = new File(fileName);
		try {
			multipartFile.transferTo(dest);
			mv.addObject("success", true);
			mv.addObject("msg", "上传文件成功");
		}catch(IllegalStateException | IOException e) {
			mv.addObject("success", false);
			mv.addObject("msg", "上传文件失败");
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * Part是由Servlet API提供的类
	 * @param partFile
	 * @return
	 */
	@RequestMapping("/uploadPart")
	public ModelAndView uploadPart(Part partFile) {
		ModelAndView mv = new ModelAndView();
		mv.setView(new MappingJackson2JsonView());
		String fileName = partFile.getSubmittedFileName(); // 获取原始文件名
		File dest = new File(fileName);
		try {
			partFile.write("d:/temp/" + fileName);
			mv.addObject("success", true);
			mv.addObject("msg", "上传文件成功");
		}catch(IllegalStateException | IOException e) {
			mv.addObject("success", false);
			mv.addObject("msg", "上传文件失败");
			e.printStackTrace();
		}
		return mv;
	}
}
