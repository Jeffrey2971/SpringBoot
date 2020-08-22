package com.jeffrey.Listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {

    public HelloSpringApplicationRunListener(SpringApplication application, String[] args){

    }
    /**
     * Called immediately when the run method has first started. Can be used for very
     * early initialization.
     */
    @Override
    public void starting() {
        System.out.println("SpringApplicationRunListener..starting");
    }

    /**
     * Called once the environment has been prepared, but before the
     * {@link } has been created.
     *
     * @param environment the environment
     */
    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("SpringApplicationRunListener..environmentPrepared" + environment.getSystemProperties().get("os.name"));
    }

    /**
     * Called once the {@link } has been created and prepared, but
     * before sources have been loaded.
     *
     * @param context the application context
     */
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener..contextPrepared" + context);
    }

    /**
     * Called once the application context has been loaded but before it has been
     * refreshed.
     *
     * @param context the application context
     */
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener..contextLoaded");
    }

    /**
     * Called immediately before the run method finishes.
     *
     * @param context   the application context or null if a failure occurred before the
     *                  context was created
     * @param exception any run exception or null if run completed successfully.
     */
    @Override
    public void finished(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("SpringApplicationRunListener..finished");
    }
}
