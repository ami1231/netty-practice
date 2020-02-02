package com.netty.nettypractice.nio.bufferdemo;

import com.netty.nettypractice.nio.bufferdemo.buffer.IntBufferDemo;

import java.nio.IntBuffer;

public class NioIntBufferReadDemo {

    public static void main(String[] args){

        IntBufferDemo intBufferDemo = new IntBufferDemo(false,30);
        //取得limit-1個元素的buffer
        IntBuffer intBuffer = intBufferDemo.getIntBuffer();

        System.out.println("Buffer create");
        System.out.println("buffer capacity = " +intBuffer.capacity());
        System.out.println("buffer position = " +intBuffer.position());
        System.out.println("buffer limit = " +intBuffer.limit());
        System.out.println();
        //切換讀取模式
        intBuffer.flip();
        System.out.println("Buffer flip , buffer from write phase change to read phase");
        System.out.println("buffer capacity = " +intBuffer.capacity());
        System.out.println("buffer position = " +intBuffer.position());
        System.out.println("buffer limit = " +intBuffer.limit());
        System.out.println();

        System.out.println("start read value");
        while(intBuffer.position()<intBuffer.limit()){
            System.out.println();
            int value = intBuffer.get();
            System.out.println(value);
            System.out.println("buffer position = " +intBuffer.position());
            System.out.println("buffer limit = " +intBuffer.limit());
        }
    }

}
