package org.example;

import java.lang.reflect.Method;

public class ReflectionTester {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("org.example.ExampleClass");

            System.out.println("Methods of " + cls.getName() + ":");
            for (Method method : cls.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Test.class) && method.getParameterCount() == 0 && java.lang.reflect.Modifier.isStatic(method.getModifiers())) {
                    System.out.println("Invoking @Test method: " + method.getName());
                    method.invoke(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
