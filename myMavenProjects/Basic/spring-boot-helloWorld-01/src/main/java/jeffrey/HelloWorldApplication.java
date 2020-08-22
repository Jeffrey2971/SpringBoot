package jeffrey;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个SpringBoot应用
 */
 @SpringBootApplication
public class HelloWorldApplication {
    public static void main(String[] args) {

        // Spring应用启动
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}
