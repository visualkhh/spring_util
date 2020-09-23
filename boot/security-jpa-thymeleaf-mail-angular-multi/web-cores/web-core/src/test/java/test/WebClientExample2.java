//package test;
//
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//public class WebClientExample2 {
//    public static void main(String[] args) throws InterruptedException {
//        WebClient webClient = WebClient.create("https://www.naver.com");
//        Mono<String> result = webClient.get()
//                .retrieve()
//                .bodyToMono(String.class);
//        result.subscribe(WebClientExample2::handleResponse);
//        System.out.println("After subscribe");
//        Thread.sleep(1000);
//    }
//
//    private static void handleResponse(String s) {
//        System.out.println("handle response");
//        System.out.println(s);
//    }
//}
