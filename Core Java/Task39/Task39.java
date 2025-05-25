package cognizant;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;

public class Task39 {

    public static void main(String[] args) {
        String className = "cognizant.MyReflectableClass";

        try {
            // Step 1: Load the class dynamically using Class.forName()
            Class<?> clazz = Class.forName(className);
            System.out.println("Successfully loaded class: " + clazz.getName());

            // Step 2: Print Method Names and Parameters using getDeclaredMethods()
            System.out.println("\n--- Declared Methods ---");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.print("  Method: " + method.getName() + " (");
                Class<?>[] paramTypes = method.getParameterTypes();
                for (int i = 0; i < paramTypes.length; i++) {
                    System.out.print(paramTypes[i].getSimpleName());
                    if (i < paramTypes.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println(")");
                System.out.println("    Return Type: " + method.getReturnType().getSimpleName());
                System.out.println("    Modifiers: " + Modifier.toString(method.getModifiers()));
            }

            // Step 3: Create an instance of the class dynamically
            System.out.println("\n--- Dynamic Instantiation ---");
            Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
            Object instance = constructor.newInstance("Reflected Object", 42);
            System.out.println("Instance created using constructor with args.");

            // Step 4: Invoke publicMethodNoArgs() dynamically
            System.out.println("\n--- Dynamic Method Invocation (publicMethodNoArgs) ---");
            Method publicMethodNoArgs = clazz.getDeclaredMethod("publicMethodNoArgs");
            publicMethodNoArgs.invoke(instance);

            // Step 5: Invoke publicMethodWithArgs() dynamically
            System.out.println("\n--- Dynamic Method Invocation (publicMethodWithArgs) ---");
            Method publicMethodWithArgs = clazz.getDeclaredMethod("publicMethodWithArgs", String.class, int.class);
            Object result = publicMethodWithArgs.invoke(instance, "Prefix", 100);
            System.out.println("Result from publicMethodWithArgs: " + result);

            // Step 6: Invoke a private method dynamically (requires setAccessible(true))
            System.out.println("\n--- Dynamic Method Invocation (privateHelperMethod) ---");
            Method privateMethod = clazz.getDeclaredMethod("privateHelperMethod", int.class);
            privateMethod.setAccessible(true); // Allow access to private method
            Object privateResult = privateMethod.invoke(instance, 5);
            System.out.println("Result from privateHelperMethod: " + privateResult);

            // Step 7: Invoke a static method dynamically
            System.out.println("\n--- Dynamic Method Invocation (staticMethod) ---");
            Method staticMethod = clazz.getDeclaredMethod("staticMethod", String.class);
            staticMethod.invoke(null, "This is a static message."); // 'null' for static method instance

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Class not found - " + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.err.println("Error: Method not found - " + e.getMessage());
        } catch (InstantiationException e) {
            System.err.println("Error: Could not instantiate class - " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Error: Access denied (e.g., trying to call private method without setAccessible) - " + e.getMessage());
        } catch (InvocationTargetException e) {
            System.err.println("Error: Method threw an exception - " + e.getTargetException().getMessage());
            e.getTargetException().printStackTrace(); // Print stack trace of the original exception
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
