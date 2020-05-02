package pub.huanxi.spring.demo;

import pub.huanxi.spring.demo.service.TestService;

public class TestController {
    @Autowired
    private TestService testService;

    public void print() {
        System.out.println(testService);
    }
}
