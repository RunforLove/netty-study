package com.sucre.JDKNioChannle;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class NioChannelTest1 {

    /**
     * 基本的Channle使用的例子
     */
    public static void main(String[] args) throws Exception{
        /**
         * rw 读写模式
         */
        RandomAccessFile file = new RandomAccessFile("/Users/shuanghun/diancan.sh", "rw");
        FileChannel inChannel = file.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(byteBuffer);

        /**
         * 一行一行的逐行读取;
         */
        while (bytesRead != -1){
            /**
             * 参考：http://book.51cto.com/art/200902/109718.html
             *
             * flip()方法用来将缓冲区准备为数据传出状态，这通过将limit设置为position的当前值，再将 position的值设为0来实现：
             */
            byteBuffer.flip();

            /**
             * 把当前行都输出
             */
            while(byteBuffer.hasRemaining()){
                System.out.println((char)byteBuffer.get());
            }

            /**
             * clear()方法将position设置为0，并将limit设置为等于capacity，
             * 从而使缓冲区准备好从缓冲区的put操作或信道的读操作接收新的数据。
             */
            byteBuffer.clear();

            /**
             * 下一行给到bytesRead，递归
             */
            bytesRead = inChannel.read(byteBuffer);
        }

        // 关闭文件读取
        file.close();
    }
}
