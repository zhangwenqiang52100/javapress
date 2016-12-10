package com.zwq.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Archer on 2016/12/10.
 */
public class Identifyingcode{

    public static boolean verifyCodeGoogle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String verifyCode = request.getParameter("code");
        String kaptchaValue = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (kaptchaValue == null || kaptchaValue == "" || !verifyCode.equalsIgnoreCase(kaptchaValue)) {
          return false;
        } else {
            return true;
        }
    }
}
