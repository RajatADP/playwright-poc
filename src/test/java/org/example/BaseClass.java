package org.example;

import com.microsoft.playwright.*;
import helpers.PageAnnotation;
import org.example.pages.HomePage;
import org.example.pages.SomeOtherPage;
import org.junit.jupiter.api.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

// Subclasses will inherit PER_CLASS behavior.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseClass {
    // Shared between all tests in the class.
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    @PageAnnotation
    protected HomePage homePage;
    @PageAnnotation
    protected SomeOtherPage someOtherPage;

    @BeforeAll
    public void launchBrowser() {
        playwright = Playwright.create();
    }

    @BeforeEach
    public void createContextAndPage() {
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
//        homePage = new HomePage(page);
//        someOtherPage = new SomeOtherPage(page);

        Class<?> clazz = this.getClass().getSuperclass();
        for (Field field: clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PageAnnotation.class)) {
                Class<?>[] type = {Page.class};
                try {
                    field.set(this, field.getType().getConstructor(type).newInstance(page));
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @AfterEach
    public void closeContext() {
        context.close();
        page.close();
    }

    @AfterAll
    public void closeBrowser() {
        browser.close();
        playwright.close();
    }
}
