package test.java;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class JavaTest {
    @Test
    public void main() {
        // some..
        System.out.println("-->"+Strings.nullToEmpty("show")+ "--");
    }
}
