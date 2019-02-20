package com.example.ooo.demo;

		import org.redisson.Redisson;
		import org.redisson.api.RScheduledExecutorService;
		import org.redisson.api.RedissonClient;
		import org.redisson.config.Config;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.boot.context.event.ApplicationReadyEvent;
		import org.springframework.context.event.EventListener;

		import java.util.concurrent.ExecutionException;
		import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	Config config;

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws ExecutionException, InterruptedException {
		RedissonClient redisson = Redisson.create(config);

		RScheduledExecutorService e = redisson.getExecutorService("myExecutor");
		e.schedule(new PlusTask("11", "22"), 5, TimeUnit.SECONDS);
		e.schedule(new PlusTask("33", "44"), 5, TimeUnit.SECONDS);
		e.schedule(new PlusTask("55", "66"), 5, TimeUnit.SECONDS);
		e.schedule(new PlusTask("77", "88"), 5, TimeUnit.SECONDS);
		e.schedule(new PlusTask("99", "00"), 5, TimeUnit.SECONDS);
	}

}
