package com.snail.redis;

import java.util.concurrent.ExecutorService;

import redis.clients.jedis.Jedis;

public class redis {
  public static void main(String[] args) {
	  @SuppressWarnings("resource")
	Jedis jedis = new Jedis("localhost",6379);
	  jedis.auth("longmao");
	 String string = jedis.get("longmao") ;
	  System.out.println(string);
  }
}
