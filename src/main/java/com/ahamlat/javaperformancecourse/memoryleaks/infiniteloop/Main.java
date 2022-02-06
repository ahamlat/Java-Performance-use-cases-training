package com.ahamlat.javaperformancecourse.memoryleaks.infiniteloop;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        String templateText = "";
        try {
            templateText = loadTemplate("template2.txt");
            String text = clearVariablesFields(templateText);
            System.out.println("Template text:");
            System.out.println(templateText);
            System.out.println("Result text:");
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String loadTemplate(String templatePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> lines = Files.lines(Paths.get(templatePath), StandardCharsets.UTF_8)) {
            lines.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        return contentBuilder.toString();
    }

    private static String clearVariablesFields(String templateText) throws InterruptedException {
        templateText = templateText.replaceAll("(\\[order.*?]|\\[default.*?])", "");
        StringBuffer finalGuaranteeText = new StringBuffer("");
        //Pattern pattern = Pattern.compile("([#@|${}]\\{.*})");
        Pattern pattern = Pattern.compile("([#@|${}])");

        Matcher matcher = pattern.matcher(templateText);
        while (matcher.find()) {
            finalGuaranteeText.append(templateText.substring(0, matcher.start()));
            templateText = templateText.substring(matcher.start());
            templateText = templateText.substring(templateText.indexOf("}") + 1);
            finalGuaranteeText.append("       ");
            finalGuaranteeText.append("       ");
            matcher = pattern.matcher(templateText);
        }
        finalGuaranteeText.append(templateText);
        return finalGuaranteeText.toString();
    }
}
