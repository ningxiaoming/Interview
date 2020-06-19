/*
package com.nxm.spring_lecture;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
*/
/*
         此代码就是加载启动类加载器main方法的核心代码
        public class MainMethodRunner{

                public void run() throws Exception {
            Class<?> mainClass = Thread.currentThread().getContextClassLoader().loadClass(this.mainClassName);
            Method mainMethod = mainClass.getDeclaredMethod("main", String[].class);
            mainMethod.invoke(null, new Object[] { this.args });
		}
	}

	位于BOOT-INF下面的classes下的业务代码：springboot的启动类由 MainMethodRunner类中的run方法进行加载的  类加载器是  springboot自己定义的类加载器
    LaunchedURLClassLoader。

    位于BOOT-INF下面的lib下的业务代码所使用的jar包：springboot项目启动所使用的jar包也是由LaunchedURLClassLoader来进行进行加载的：首先试用getClassPathArchives方法
    将jar包的路径存储到一个集合中然后再由LaunchedURLClassLoader加载

 *//*

*/
/*
    java调试协议
    java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5050 -jar spring_lecture-0.0.1-SNAPSHOT.jar
 *//*

@SpringBootApplication
public class SpringLectureApplication {

    public static void main(String[] args) {
        */
/*
            当在idea中运行的时候：加载此类的类加载器是由系统类加载（AppClassLoader）加载的。
            当把项目打成jar包的时候： 加载此类的类加载器是有springboot自己定义的类加载器(LaunchedURLClassLoader)来加载的。
        *//*

        System.out.println(SpringLectureApplication.class.getClassLoader());
        SpringApplication.run(SpringLectureApplication.class, args);
        SpringApplication springApplication = new SpringApplication(SpringLectureApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);

    }

}
*/
