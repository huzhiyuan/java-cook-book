package com.hzy.spring.boot.hello;

/**
 * @Author:huzhiyuan
 * @Date: 2019/10/17
 * @Description:
 * @version: 1.1
 */
public class Greeting {
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
