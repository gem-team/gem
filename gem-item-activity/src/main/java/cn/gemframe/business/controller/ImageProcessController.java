package cn.gemframe.business.controller;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import cn.gemframe.business.service.BxlcTaskListeningImpl;
import cn.gemframe.config.exception.GemFrameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageProcessController {

	@Autowired
	private BxlcTaskListeningImpl bxlcTaskListeningImpl;

	/**
	 * @Description:查看流程图
	 * @param id 流程主键
	 * @author: 赵兴炎
	 * @date 2018年11月18日
	 */
	@GetMapping("image")
	public void image(HttpServletResponse response, String id) {
		InputStream pngLn =null;
		ServletOutputStream outputStream =null;
		try {
			pngLn = bxlcTaskListeningImpl.image(id);
			response.setContentType("multipart/form-data");
			outputStream = response.getOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024 * 10];
			while ((len = pngLn.read(buffer)) != -1){
				outputStream.write(buffer,0,len);
			}
			outputStream.flush();
		} catch (Exception e) {
			throw new GemFrameException("图片读取异常");
		}finally {
			try {
				if(pngLn!=null) {
					pngLn.close();
				}
				if(outputStream!=null) {
					outputStream.close();
				}
			} catch (Exception e) {
				throw new GemFrameException("IO关闭异常");
			}
		}
	}
}
