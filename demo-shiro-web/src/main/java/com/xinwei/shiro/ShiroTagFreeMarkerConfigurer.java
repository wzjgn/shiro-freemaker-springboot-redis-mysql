package com.xinwei.shiro;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.xinwei.shiro.tag.ShiroTags;

import freemarker.template.TemplateException;

public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer{  
    @Override  
    public void afterPropertiesSet() throws IOException, TemplateException {  
        super.afterPropertiesSet();  
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());  
    }
}