package cqut.guobenqi.online_xdclass;


import cqut.guobenqi.online_xdclass.model.entity.User;
import cqut.guobenqi.online_xdclass.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class OnlineXdclassApplicationTests {

	@Test
	public void testJwt(){
		User user = new User();

		user.setName("张三");
		user.setCreateTime(new Date());

		String token = JWTUtils.geneJsonWebToken(user);

		System.out.println(token);

		Claims claims = JWTUtils.checkJWT(token);

		System.out.println(claims.get("name"));
		System.out.println(claims.get("phone"));
		System.out.println(claims.get("pwd"));
	}
}
