package com.ahamlat.javaperformancecourse.forkjoin;

import com.ahamlat.javaperformancecourse.Tools;

import java.util.List;

public abstract class ForkJoinFindOccurences {

    static int NUMBER_OF_ITERATIONS = 100;
    static final String TO_FIND = "who";
    static List<String> WORDS_LIST;

    static {
        try {
            WORDS_LIST = Tools.initList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
