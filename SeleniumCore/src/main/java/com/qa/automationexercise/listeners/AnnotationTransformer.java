package com.qa.automationexercise.listeners;


import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;

public class AnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, java.lang.reflect.Constructor testConstructor, java.lang.reflect.Method testMethod) {
        // Set the retry analyzer for all test methods
        annotation.setRetryAnalyzer(Retry.class);
    }
}
