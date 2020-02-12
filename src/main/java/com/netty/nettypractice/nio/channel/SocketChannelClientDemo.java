package com.netty.nettypractice.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelClientDemo {

    public static void main(String[] args) throws IOException {

        //取得socket channel
        SocketChannel socketChannel = SocketChannel.open();
        //走非阻塞模式
        socketChannel.configureBlocking(false);

        //連線主機資訊
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9001));

        //進行等待連接成功
        while (!socketChannel.finishConnect()){
            System.out.println("non-success connect server");
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(300);
        byteBuffer.put(new String("Hello World").getBytes());
        byteBuffer.flip();
        //因為SocketChannel要寫入資料時,需要從ByteBuffer讀取資料,所以需要將ByteBuffer切換成讀取模式
        //先讀取,才可以將資料寫出
        socketChannel.write(byteBuffer);
        socketChannel.shutdownOutput();
        socketChannel.close();



    }

}
