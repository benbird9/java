package types;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.function.Function;

public class GenericClass<T> {
  private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) { };
  private final Type type = typeToken.getType(); // or getRawType() to return Class<? super T>

  public Type getType() {
    return type;
  }

  public static void main(String[] args) {
    GenericClass<String> example = new GenericClass<String>() { };
    Function<String, Integer> func = s -> Integer.valueOf(s);
    Type[] types = func.getClass().getGenericInterfaces();
    System.out.println(func.getClass().getGenericInterfaces());
    System.out.println(example.getType()); // => class java.lang.String
  }
}