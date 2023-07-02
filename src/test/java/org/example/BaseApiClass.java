package org.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

// Subclasses will inherit PER_CLASS behavior.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseApiClass {
    // Shared between all tests in the class.
    Playwright playwright;

    @BeforeAll
    void createPlaywright() {
        playwright = Playwright.create();
    }

    @AfterAll
    void closeBrowser() {
        playwright.close();
    }

}
