package com.netty.nettypractice.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * server端 channel
 */
public class ServerSocketChannelDemo {

    public static void main(String[] args) throws IOException {

        //獲取nio選擇器
       Selector selector  = Selector.open();

       ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
       serverSocketChannel.configureBlocking(false);
       //bind哪一個通道
       serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",9001));
       //加入選擇器事件
       serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
       while(selector.select()>0){
           System.out.println(selector.select());
           Set<SelectionKey> keys = selector.selectedKeys();
           Iterator<SelectionKey> selectionKeyIterator = keys.iterator();
           while (selectionKeyIterator.hasNext()){
               SelectionKey key = selectionKeyIterator.next();
               //serverSocketChannel 只做accept事件
               if(key.isAcceptable()){
                   ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                   SocketChannel socketChannel = channel.accept();
                   socketChannel.configureBlocking(false);
                   socketChannel.register(selector,SelectionKey.OP_READ);
               } else if(key.isReadable()){
                   SocketChannel socketChannel = (SocketChannel)key.channel();
                   ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                   //判斷是否還有數據,將傳入資料寫入byteBuffer
                   int length = 0;
                   while ((length=socketChannel.read(byteBuffer))>0){
                       //切成讀模式
                       byteBuffer.flip();
                       System.out.println("data = "+new String(byteBuffer.array(),0,length));
                       //切成寫入模式,提供數據寫入
                       byteBuffer.clear();
                   }
                   socketChannel.close();
               }
               selectionKeyIterator.remove();
           }

       }
       serverSocketChannel.close();
    }

}
