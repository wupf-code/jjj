package com.example.jjj.demos.web.controller;//KaptchaController.java
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KaptchaContorller {

    @Autowired
    private Producer defaultKaptcha = null;

    @RequestMapping("/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);

        // 告诉浏览器不要缓存,防止生成同样的验证码图片
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");

        // 返回信息为jpg
        response.setContentType("image/jpeg");

        // 生成验证码
        String capText = defaultKaptcha.createText();

        // 将验证码生成的文字保存到session 中,等待比对
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bufferedImage = defaultKaptcha.createImage(capText);
        // 将图片写到response中返回s
        try {

            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}