package com.github.yanzheshi;

class HelloA{
    public HelloA(){
        System.out.println("helloA");
    }

    {
        System.out.println("i m class a");
    }

    static {
        System.out.println("static a");
    }

}

public class HelloB  extends HelloA{
    public HelloB(){
        System.out.println("helloB");
    }

    {
        System.out.println("i m class b");
    }

    static {
        System.out.println("static b");
    }

    public static void main(String[] args) {
        new HelloB();
    }
}


