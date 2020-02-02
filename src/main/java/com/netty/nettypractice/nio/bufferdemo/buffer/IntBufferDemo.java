package com.netty.nettypractice.nio.bufferdemo.buffer;

import lombok.Data;

import java.nio.IntBuffer;

@Data
public class IntBufferDemo {

    private IntBuffer intBuffer = IntBuffer.allocate(50);

    public IntBufferDemo(Boolean isClear,Integer limit){
        if(!isClear){
            //設置Buffer的limit上限,因此只能裝載limit-1的數量
            intBuffer.limit(limit);

            for(int i = 1 ; i <= intBuffer.limit()-1;i++){
                intBuffer.put(i);
            }
        }
    }

}