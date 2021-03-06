package com.business.erp.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FreemarkerController {
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping("/genHtml")
    @ResponseBody
    public String genFreemarker() throws IOException, TemplateException {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("hello.ftl");
        Map data = new HashMap();
        data.put("hello", "这是spring整合生成的freemarker");
        Writer out = new FileWriter(new File("/Folder/temp/out.html"));
        template.process(data, out);
        out.close();
        return "ok";
    }

}
