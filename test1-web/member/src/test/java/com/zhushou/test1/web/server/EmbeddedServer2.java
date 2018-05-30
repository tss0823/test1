package com.zhushou.test1.web.server;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class EmbeddedServer2 {
    public static void main(String[] args) throws Exception {
//		System.setProperty("hongbaoAppType","proxy");
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(1024);
        tomcat.setBaseDir("target/tomcat");
        tomcat.addWebapp("/", new File("/u01/workspace/test1/test1-web/member/src/main/webapp").getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}


