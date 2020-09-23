//package test;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//public class WebTest {
//    public static void main(String[] args) throws Exception {
//
//        WebClient client = WebClient.create("https://www.naver.com");
//        WebClient.Builder builder = WebClient.builder();
//        Mono<String> result = client.get()
//                .retrieve()
//                .bodyToMono(String.class);
//        result.subscribe(it-> {
//            System.out.println(it);
//        });
//        result.block();
//
//    }
//}
