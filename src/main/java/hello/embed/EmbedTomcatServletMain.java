package hello.embed;

import hello.servlet.HelloServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.nio.file.Files;

public class EmbedTomcatServletMain {
    public static void main(String[] args) throws LifecycleException, IOException {
        System.out.println("EmbedTomcatServletMain.main");
        // 톰캣 설정
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        /**
         * docBase 이란?
         * 해당 웹어플리케이션에 대한 Document Base (Context Root로도 알려져 있습니다) 디렉토리,
         * 또는 웹어플리케이션 아카이브 파일의 경로명(웹어플리케이션을 WAR 파일로 직접 실행하는 경우)을 나타냅니다.
         * 이 디렉토리나 WAR 파일에에 대한 절대경로명을 지정할 수도 있고,
         * 이 Context가 정의된 Host의 appBase 디렉토리에 대한 상대경로명을 지정할 수도 있습니다
         */

        //서블릿 등록
        String docBase = Files.createTempDirectory("tomcat-basedir").toString(); // 윈도우의 경우 docBase 에 해당하는 디렉토리를 생성해 주어야 실행됨..
        Context context = tomcat.addContext("", docBase);
        tomcat.addServlet("", "helloServlet", new HelloServlet());
        context.addServletMappingDecoded("/hello-servlet", "helloServlet");
        tomcat.start();
    }
}
