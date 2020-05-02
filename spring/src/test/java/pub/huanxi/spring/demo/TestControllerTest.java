package pub.huanxi.spring.demo;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class TestControllerTest {

    @Test
    public void test() {
        TestController testController = new TestController();
        Field[] declaredFields = testController.getClass().getDeclaredFields();
        Arrays.stream(declaredFields).forEach(field -> {
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation != null) {
                try {
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    // 该例子中使用的 type.getConstructor().newInstance();
                    // Spring 中使用的是 IOC 容器获取
                    Object o = type.getConstructor().newInstance();
                    field.set(testController, o);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            testController.print();
        });
    }
}