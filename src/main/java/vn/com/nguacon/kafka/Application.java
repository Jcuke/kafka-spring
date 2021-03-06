package vn.com.nguacon.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import vn.com.nguacon.kafka.consumer.Consumer;

@EnableIntegration
@SpringBootApplication
@PropertySources({
	@PropertySource("classpath:application.properties"),
	@PropertySource(value = "file:config/application.properties", ignoreResourceNotFound = true)
})
public class Application extends WebMvcConfigurerAdapter implements CommandLineRunner {
	
	@Autowired
	ConfigurableApplicationContext context;
	
	@Autowired
	private Consumer consumer;
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		//TEST pub/sub kafka topic
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(50);
		executor.createThread(consumer).start();

		//Producer producer = context.getBean("kafkaProducer", Producer.class);
		//for (int i = 0; i < 10; i++) {
		//	producer.send("Kafka say hello ---+++ " + i);
		//	Thread.sleep(1000);
		//}
	}
}
