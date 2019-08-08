package redis.testsubscribe;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author yangjie
 * @date 2019/3/23 14:19
 */
public class RedisSubscribeTwo extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(channel + "发布了：  " + message);
    }


    /**
     * 运行方法，启动监听者， 监听主题
     * @param args
     */
    public static void main(String[] args) {

        Jedis jedis = new Jedis("47.110.159.8", 6379);

        RedisSubscribeTwo redisSubscribeOne = new RedisSubscribeTwo();

        jedis.subscribe(redisSubscribeOne, "channel");

    }
}
