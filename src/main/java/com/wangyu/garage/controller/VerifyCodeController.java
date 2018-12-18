package com.wangyu.garage.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import com.wangyu.prm.constant.SessionAttributeConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 验证码Controller
 */
@Controller
@RequestMapping(value="/Verify")
public class VerifyCodeController extends BaseController {

	@RequestMapping(value="/Code")
	public void getCode() throws IOException
	{
		int width = 77 ;
		int height = 37 ;
		int fontHeight = 22;
		
		BufferedImage bufferimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		
		Graphics gp = bufferimg.getGraphics();
		
		Color color = new Color(225, 215, 240);
		gp.setColor(color);
		gp.fillRect(0, 0, width, height);
		
		Font font = new Font("Fixedsys",Font.BOLD,fontHeight);
		gp.setFont(font);
		
		gp.setColor(Color.BLUE);
		Random random = new Random();
		for(int i = 0 ; i < 15 ; i++)
		{
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int x2 = random.nextInt(width);
			int y2 = random.nextInt(height);
			
			gp.drawLine(x1,y1, x2, y2);
		}
		
		String text= getText();
		//添加 session ֵ
		request.getSession().setAttribute(SessionAttributeConstants.LOGINCHECKCODE, text);
		
		//绘制字
		gp.drawString(text, 10, 26);
		
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(bufferimg, "jpg", sos);
		sos.close();
		
	}
	
	private String getText()
	{
		Random random = new Random();
		String text ="";
		for(int i = 0 ; i < 4; i++)
		{
			text += random.nextInt(10);
		}
		return text;
	}
	

}
