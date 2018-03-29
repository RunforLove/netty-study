package com.sucre.buffer;

import java.nio.IntBuffer;

public class NioBufferTest2 {

    public static void main(String args[]){

        /**
         * 这里的单位是Int个字节；8 * 4(Byte)
         */
        IntBuffer intBuffer = IntBuffer.allocate(8);
        printIntBufferWrite(intBuffer);

        intBuffer.put(123);
        printIntBufferWrite(intBuffer);

        intBuffer.put(456);
        printIntBufferWrite(intBuffer);

        intBuffer.flip();
        printIntBufferRead(intBuffer);

        intBuffer.clear();
        printIntBufferWrite(intBuffer);
        intBuffer.put(888);
        intBuffer.put(999);
        printIntBufferWrite(intBuffer);

        intBuffer.flip();
        printIntBufferRead(intBuffer);
        while (intBuffer.hasRemaining()){
            System.err.println(intBuffer.get());
        }
        printIntBufferRead(intBuffer);
    }

    public static void printIntBufferWrite(IntBuffer intBuffer){
        System.out.println("Write Mode: ");
        System.out.println("\tCapacity: " + intBuffer.capacity());
        System.out.println("\tPosition: " + intBuffer.position());
        System.out.println("\tLimit: " + intBuffer.limit());
    }

    public static void printIntBufferRead(IntBuffer intBuffer){
        System.out.println("Read Mode: ");
        System.out.println("\tCapacity: " + intBuffer.capacity());
        System.out.println("\tPosition: " + intBuffer.position());
        System.out.println("\tLimit: " + intBuffer.limit());
    }
}
