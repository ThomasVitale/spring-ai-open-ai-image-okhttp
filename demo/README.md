# Demo

## Pre-requisites

Make sure you have an [OpenAI account](https://platform.openai.com/signup). The application relies on an OpenAI API for providing LLMs.
Define an environment variable with the OpenAI API Key associated to your OpenAI account as the value.

```shell
export SPRING_AI_OPENAI_API_KEY=<INSERT KEY HERE>
```

## Running the application

Run the Spring Boot application as follows to get Testcontainers automatically provision a Grafana LGTM observability stack.

```shell
./gradlew bootTestRun
```

Grafana is listening to port 3000. Check your container runtime to find the port to which is exposed to your localhost
and access Grafana from http://localhost:. The credentials are `admin`/`admin`.

The application is automatically configured to export metrics and traces to the Grafana LGTM stack via OpenTelemetry.
In Grafana, you can query the traces from the "Explore" page, selecting the "Tempo" data source. You can also visualize metrics in "Explore > Metrics".

## Calling the application

You can now call the application that will use OpenAI to perform generative AI operations.
This example uses [httpie](https://httpie.io) to send HTTP requests.

```shell
http :8080/image
```

The request will fail with a timeout exception from OkHttp3, which is used under the hood by RestClient.

Now, uncomment this code in `DemoApplication` to force using a JDK-based implementation for RestClient.

```java
@Bean
RestClientCustomizer restClientCustomizer1() {
    return builder -> builder.requestFactory(ClientHttpRequestFactories.get(SimpleClientHttpRequestFactory.class, ClientHttpRequestFactorySettings.DEFAULTS));
}
```

Re-run the application and call the endpoint.

```shell
http :8080/image
```

Now the request succeeds.

Try now commenting again the previous code, and uncomment the following one to use the default RestClient implementation (OkHttp3 in this case), but customizing the timeout.

```java
@Bean
RestClientCustomizer restClientCustomizer2() {
    return restClientBuilder -> {
        restClientBuilder
                .requestFactory(new BufferingClientHttpRequestFactory(
                        ClientHttpRequestFactories.get(ClientHttpRequestFactorySettings.DEFAULTS
                                .withConnectTimeout(Duration.ofSeconds(10))
                                .withReadTimeout(Duration.ofSeconds(60)))));
    };
}
```

Re-run the application and call the endpoint.

```shell
http :8080/image
```

The request still succeeds.

Try now commenting again the previous code. This time will have Spring Boot use the Apache HttpClient automatically for the RestClient implementation.

Uncomment the following dependency in the build.gradle file.

```groovy
implementation 'org.apache.httpcomponents.client5:httpclient5'
```

Re-run the application and call the endpoint.

```shell
http :8080/image
```

The request still succeeds.
