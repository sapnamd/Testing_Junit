package com.example.service;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
public class UnitTestGenerator {


	    public static void generateTests(Class<?> clazz) {
	        Method[] methods = clazz.getDeclaredMethods();

	        for (Method method : methods) {
	            if (Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
	                generateTestMethod(method);
	            }
	        }
	    }

	    private static void generateTestMethod(Method method) {
	        String methodName = method.getName();
	        Class<?>[] parameterTypes = method.getParameterTypes();
	        String testMethodName = "test" + methodName;

	        StringBuilder testCode = new StringBuilder();
	        testCode.append("@Test\n");
	        testCode.append("public void ").append(testMethodName).append("() {\n");

	        // Generate code to instantiate the class (if needed)
	        if (!Modifier.isStatic(method.getModifiers())) {
	            testCode.append("    ").append(method.getDeclaringClass().getSimpleName()).append(" instance = new ")
	                    .append(method.getDeclaringClass().getName()).append("();\n");
	        }

	        // Generate code to invoke the method with sample arguments
	        testCode.append("    // Provide sample arguments for the method\n");
	        for (int i = 0; i < parameterTypes.length; i++) {
	            testCode.append("    ").append(parameterTypes[i].getSimpleName()).append(" arg").append(i + 1)
	                    .append(" = getSample").append(parameterTypes[i].getSimpleName()).append("();\n");
	        }
	        testCode.append("\n");

	        // Generate code to call the method and verify the result
	        testCode.append("    // Invoke the method and verify the result\n");
	        if (method.getReturnType() != void.class) {
	            testCode.append("    ").append(method.getReturnType().getSimpleName()).append(" result = ");
	        }
	        if (Modifier.isStatic(method.getModifiers())) {
	            testCode.append(method.getDeclaringClass().getSimpleName()).append(".");
	        } else {
	            testCode.append("instance.");
	        }
	        testCode.append(methodName).append("(");
	        for (int i = 0; i < parameterTypes.length; i++) {
	            testCode.append("arg").append(i + 1);
	            if (i < parameterTypes.length - 1) {
	                testCode.append(", ");
	            }
	        }
	        testCode.append(");\n");

	        // Generate code to assert the expected result (if applicable)
	        if (method.getReturnType() != void.class) {
	            testCode.append("    // Assert the expected result\n");
	            testCode.append("    // assertEquals(expectedResult, result);\n");
	        }

	        testCode.append("}\n\n");

	        System.out.println(testCode.toString());
	    }

	    // Helper method to generate sample arguments of various types (for demonstration purposes)
	    private static int getSampleInt() {
	        return 42;
	    }

	    private static String getSampleString() {
	        return "Hello, world!";
	    }

	    public static void main(String[] args) {
	        generateTests(MyClass.class);
	    }
	}

	class MyClass {
	    public static int add(int a, int b) {
	        return a + b;
	    }

	    public static String greet(String name) {
	        return "Hello, " + name + "!";
	    }
	}
