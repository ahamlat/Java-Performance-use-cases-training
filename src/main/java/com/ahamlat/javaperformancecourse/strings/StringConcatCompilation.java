package com.ahamlat.javaperformancecourse.strings;

public class StringConcatCompilation {
    public StringConcatCompilation() {
        String s = "";
        for(int i = 0; i < 100; i++) {
            s = s+i;
        }
    }
}