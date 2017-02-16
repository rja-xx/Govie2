package se.rj.govie;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.rj.govie.config.GovieContext;

public class Runner {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(GovieContext.class).getBean(GovieServer.class).start();
    }
}
