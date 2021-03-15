package cqut.guobenqi.online_xdclass.utils;

import cqut.guobenqi.online_xdclass.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT工具类
 * */
public class JWTUtils {
    private static final long EXPIRE = 60000 *60 * 24 * 7;//过期时间

    private static final String SECRET = "xdclass.net1666";//加密密钥

    private static final String TOKEN_PREFIX = "xdclass";

    private static final String SUBJECT = "xdclass";

    public static String geneJsonWebToken(User user){
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img", user.getHeadImg())
                .claim("id", user.getId())
                .claim("name", user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
        token = TOKEN_PREFIX + token;
        return token;
    }
    public static Claims checkJWT(String token){
        try{
            Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            return body;
        }catch (Exception e){
            return null;
        }

    }
}
