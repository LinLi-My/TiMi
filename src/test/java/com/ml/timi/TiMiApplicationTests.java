package com.ml.timi;

import com.ml.timi.model.entity.User;
import com.ml.timi.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TiMiApplicationTests {

    @Test
    void testJWT() {
        User user = new User();
        user.setName("xdclass");
        user.setId(18);
        user.setHeadImg("pp");

        String token = JWTUtils.geneJsonWebToken(user);
        System.out.println(token);

       /* try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        Claims claims = JWTUtils.checkJWT(token);
        System.out.println(claims.get("name"));

    }

}
