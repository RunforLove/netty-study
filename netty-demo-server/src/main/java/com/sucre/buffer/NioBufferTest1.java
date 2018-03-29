package com.sucre.buffer;

import java.nio.IntBuffer;

/**
 * @author shuanghun
 */
public class NioBufferTest1 {

    /**
     * 网络编程中,数据的读取和写入，需要有用户进程,操作系统内存(buffer)和Channel的概念;
     *
     * 1. 当我们需要与NIO Channel进行交互时,我们就需要使用到NIO buffer,即数据从Buffer读取到Channel中,或者数据从Channel中写入到Buffer中;
     * 2. 本质上Buffer就是一块内存区域,我们可以在内存区域读写；而NIO Buffer其实是这样的内存块的一个封装,并提供了一些操作方法让我们能够方便地进行数据的读写;
     * 3. ByteBuffer CharBuffer DoubleBuffer FloatBuffer IntBuffer LongBuffer ShortBuffer
     *
     * @param args
     */
    public static void main(String[] args){
        IntBuffer intBuffer = IntBuffer.allocate(5);
        intBuffer.put(123);
        intBuffer.put(456);

        //调用 Buffer.flip()方法, 将 NIO Buffer 转换为读模式.
        intBuffer.flip();

        // 每调用一次get方法读取数据时,buffer的读指针都会向前移动一个单位长度(demo中是一个int长度)
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

        //调用 Buffer.clear() 或 Buffer.compact()方法, 将 Buffer 转换为写模式.
        // 这部分逻辑：读的时候，要切换成读模式;写的时候，要切换成写模式;
        intBuffer.clear();
        intBuffer.put(999);
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }

}
