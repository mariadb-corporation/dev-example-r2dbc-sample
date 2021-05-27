package com.example.demo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	TasksRepository repository;
	
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		DemoApplication application = context.getBean(DemoApplication.class);

		//Task task = new Task("Task 5");
		//application.saveTask(task).subscribe();

		//application.getTasks().subscribe(result -> System.out.println(result));

		application.getTasks()	
				   .subscribe(new Subscriber<Task>() {
						private Subscription s;
						int onNextAmount;
						int requestAmount = 3;
					
						@Override
						public void onSubscribe(Subscription s) { 
							System.out.println("onSubscribe");
							this.s = s;
							
							System.out.println("Request (" + requestAmount + ")");
							s.request(requestAmount);
						}
					
						@Override
						public void onNext(Task task) {

							// Do something here!

							onNextAmount++;

							System.out.println("Task received: " + task.toString());

							if (onNextAmount % requestAmount == 0) {
								System.out.println("Request (" + requestAmount + ")");
								s.request(requestAmount);
							}
						}
					
						@Override
						public void onError(Throwable t) {
							System.out.println("onError");
						}
					
						@Override
						public void onComplete() {
							System.out.println("onComplete");
						}
					});


		// Prevent the application from exiting
		Thread.currentThread().join();
	}

	private Mono<Task> saveTask(Task task) {
		return repository.save(task);
	}

	private Flux<Task> getTasks() {
		return repository.findAll();
	}
}

interface TasksRepository extends ReactiveCrudRepository<Task, Integer> {
}

@Data
@RequiredArgsConstructor
@Table("tasks")
class Task {
	@Id
	private Integer id;
	@NonNull
	private String description;
	private Boolean completed;
}
