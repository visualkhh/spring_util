package test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;

public class JWTTest {
    public static void main(String[] args) {
        String jwtString = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("issueDate", System.currentTimeMillis())
                .setSubject("내용")
                .signWith(SignatureAlgorithm.HS512, "aaaa")
                .compact();
        System.out.println(jwtString);

        jwtString = "eyJ0eXAiOiJKV1QiLCJpc3N1ZURhdGUiOjE1ODIxNTg2NzA0NzgsImFsZyI6IkhTNTEyIn0.eyJzdWIiOiLrgrTsmqkifQ.u7FLKhOftKgbSc3vOYAv9z7eYMo3cUQ2eBG_WRECkr-AdrtCXfBKkrCsHPC2Q_oAf5RTXc3PT_-KFE6JZQ4Q5g";
        String[] split = jwtString.split("\\.");
        Arrays.asList(split).stream().forEach(it -> {
            try {
                Decoder decoder = Base64.getDecoder();
                byte[] decodedBytes = decoder.decode(it.getBytes());
                System.out.println(new String(decodedBytes));
            } catch (Exception e) {
            }
        });


        // Base64 인코딩 ///////////////////////////////////////////////////
//        Encoder encoder = Base64.getEncoder(); byte[] encodedBytes = encoder.encode(targetBytes);
        // Base64 디코딩 ///////////////////////////////////////////////////
//        Decoder decoder = Base64.getDecoder(); byte[] decodedBytes = decoder.decode(encodedBytes);


//        Claims aaaa = Jwts.parser().setSigningKey("aaaa").parseClaimsJws(jwtString).getBody();
        Jws<Claims> aaaa1 = Jwts.parser().setSigningKey("aaaa").parseClaimsJws(jwtString);
//        System.out.println(aaaa.toString());
        System.out.println(aaaa1.toString());


//        Jws<Claims> claimsJws = Jwts.parser().parseClaimsJws(jwtString);
//        System.out.println(claimsJws.toString());
//        String jwt = Jwts.builder()
//                .setHeaderParam("typ", "JWT")
//                .setHeaderParam("regDate", System.currentTimeMillis())
//                .setSubject(subject)
//                .claim(key, jwtString)
//                .signWith(SignatureAlgorithm.HS256, this.generateKey())
//                .compact();

    }
}
