package rs.math.oop1.z200208.transformacije.z01.crtanjeTransformacije;

import java.util.function.Consumer;

public class FxUtil {
   public static <T> T build(T node, Consumer<T> initializer) {
      initializer.accept(node);
      return node;
   }
}
