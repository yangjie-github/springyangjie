package redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author yangjie
 * @date 2019/3/23 10:21
 */
public class RedisTest {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("47.110.159.8", 6379);

        jedis.select(5);

        System.out.println(jedis.ping());
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }

        jedis.close();
    }
}
