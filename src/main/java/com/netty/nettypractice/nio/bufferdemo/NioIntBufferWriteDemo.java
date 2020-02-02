package com.netty.nettypractice.nio.bufferdemo;

import com.netty.nettypractice.nio.bufferdemo.buffer.IntBufferDemo;

import java.nio.IntBuffer;

public class NioIntBufferWriteDemo {

    public static void main(String[] args){

        IntBufferDemo intBufferDemo = new IntBufferDemo(true,null);
        IntBuffer intBuffer = intBufferDemo.getIntBuffer();

        System.out.println("Buffer create");
        System.out.println("buffer capacity = " +intBuffer.capacity());
        System.out.println("buffer position = " +intBuffer.position());
        System.out.println("buffer limit = " +intBuffer.limit());
        System.out.println();
        for(int i = 1 ; i <= intBuffer.capacity();i++){
            System.out.println("buffer put value = " +i+" =====================");
            intBuffer.put(i);
            System.out.println("buffer capacity = " +intBuffer.capacity());
            System.out.println("buffer position = " +intBuffer.position());
            System.out.println("buffer limit = " +intBuffer.limit());
            System.out.println();

            //在40的位置時,限制該Buffer總容量為50,但只能擺入42個元素
            //因此在這會爆出BufferOverFlowException
//            if(i == 40){
//                intBuffer.limit(42);
//            }

        }



        //因為capacity 只有50 當在第51的位置再放入值時,Buffer內的array塞不下
        //BufferOverflowException 拋出該錯誤
        intBuffer.put(51);


    }

}
