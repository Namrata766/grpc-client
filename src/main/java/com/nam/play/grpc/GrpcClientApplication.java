package com.nam.play.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootApplication
public class GrpcClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcClientApplication.class, args);

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
				.usePlaintext()
				.build();

		HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

		HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
				.setFirstName("Namrata")
				.setLastName("Kumari")
				.build());

		channel.shutdown();
		
		System.out.println(helloResponse.getGreeting());
	}

}
