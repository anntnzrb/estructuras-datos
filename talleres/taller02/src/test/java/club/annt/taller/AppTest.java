/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package club.annt.taller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void appHasAGreeting() {
        final App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(),
                      "app should have a greeting");
    }
}
