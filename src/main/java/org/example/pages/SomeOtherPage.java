package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SomeOtherPage {
    private final Page page;
    private final Locator input;

    public SomeOtherPage(Page page) {
        this.page = page;
        this.input = page.locator("input");
    }

    public void check() {
        input.check();
    }
}
