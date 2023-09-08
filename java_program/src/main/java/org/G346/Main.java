package org.G346;

import lombok.Data;

@Data
public class Main {
    private String name;


    public static void main(String[] args) {
        System.out.println("Hello world!");
        final Main main = new Main();
        main.setName("string");
        System.out.println(main.getName());
    }
}