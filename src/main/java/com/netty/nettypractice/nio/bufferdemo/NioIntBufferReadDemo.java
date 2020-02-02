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
            int value = intBuffer.get();
            System.out.println(value);
            System.out.println("buffer position = " +intBuffer.position());
            System.out.println("buffer limit = " +intBuffer.limit());
            System.out.println();
        }

        //讀取完成後要切換回寫模式
        System.out.println("change to write phase");
        intBuffer.clear();

        //切換回寫入模式時,可以調用clear or compact
        //clear or compact會重置position = 0 ,limit = capacity
        //若上次讀取未全部讀完,例如read limit = 40 position= 20,
        // clear會將read cache直接清空
        // compact則會將未讀的資料放入寫入狀態的array內,所以呼叫compact,position初始位置會在20

//        demoClear(intBuffer);
//        demoCompact(intBuffer);
    }

    public static void demoClear(IntBuffer intBuffer){
        //重新裝載資源
        fullValue(intBuffer);
        //切換讀模式,讀取到一半打斷
        readAndBreakValue(intBuffer);
        intBuffer.clear();
        System.out.println("buffer capacity = " +intBuffer.capacity());
        System.out.println("buffer position = " +intBuffer.position());
        System.out.println("buffer limit = " +intBuffer.limit());


    }

    public static void demoCompact(IntBuffer intBuffer){
        //重新裝載資源
        fullValue(intBuffer);
        //切換讀模式,讀取到一半打斷
        readAndBreakValue(intBuffer);
        intBuffer.compact();
        System.out.println("buffer capacity = " +intBuffer.capacity());
        System.out.println("buffer position = " +intBuffer.position());
        System.out.println("buffer limit = " +intBuffer.limit());
    }

    public static void fullValue(IntBuffer intBuffer){
        for(int i = 1 ; i <= intBuffer.limit()-1;i++){
            intBuffer.put(i);
        }
    }

    public static void readAndBreakValue(IntBuffer intBuffer){
        intBuffer.flip();
        for(int i = 1 ; i <intBuffer.limit();i++){
            System.out.println(intBuffer.get());
            if(i==10){
                break;
            }
        }

    }

}
