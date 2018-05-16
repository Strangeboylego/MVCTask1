package ua.com.vakoms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import ua.com.vakoms.Service.ServiceCount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
@Controller
public class MainController {
    @Autowired
    ServiceCount count;

    @RequestMapping("/index.html")
    public ModelAndView home() {
        return new ModelAndView("index");
    }
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public void show(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file1 =  new File("/home/halushko/Projects/MVCTask1/src/main/resources/static/text");
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file1));
        String mimeType = URLConnection.guessContentTypeFromStream(inputStream);
        if(mimeType== null){
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int)file1.length());
        response.setHeader("Content-Disposition",String.format("attachment; filename=\"%s\"",file1.getName()));
        FileCopyUtils.copy(inputStream,response.getOutputStream());
    }

    @PostMapping("/count")
    public String makeCount(@RequestParam("file")File file) throws IOException {
        count.getCount(file);
        return "index";
    }
}
