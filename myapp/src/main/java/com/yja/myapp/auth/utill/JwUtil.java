package com.yja.myapp.auth.utill;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yja.myapp.auth.entity.Login;
import lombok.Data;

import java.util.Date;

public class JwUtil {
    // 임의의 서명값
    public String secret = "your-secret";
    public final int TOKEN_TIMEOUT =  1000 * 60 * 24 * 7;

    public String createToken(long id, String username, String nickname){
        // 토큰 생성시간 만료시간
        Date now = new Date();

        // 만료시간 : 1차인증 이런 게 잘 걸려있으면 큰 문제는 안됨
        // 길게 잡으면 7~30일 / 보통은 1시간에서 3시간 / 짧게는 5~15분
        Date exp = new Date(now.getTime() + TOKEN_TIMEOUT);

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withSubject(String.valueOf(id))
                .withClaim("username",username)
                .withClaim("nickname", nickname)
                .withIssuedAt(now).withExpiresAt(exp).sign(algorithm);


    }
}
