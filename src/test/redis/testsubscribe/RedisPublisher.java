package redis.testsubscribe;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author yangjie
 * @date 2019/3/23 14:19
 */
public class RedisPublisher {

    /**
     * 运行方法，启动发布者， 监听主题
     * @param args
     */
    public static void main(String[] args) {

        Jedis jedis = new Jedis("47.110.159.8", 6379);

        RedisPublisher redisSubscribeOne = new RedisPublisher();

        jedis.publish("channel", "今天下午聚餐，吃好的");

    }
}
