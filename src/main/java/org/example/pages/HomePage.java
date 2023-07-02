package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import helpers.Constants;
import helpers.EnvironmentVariableReader;

public class HomePage
{
    private final Page page;
    private final Locator nameInput;

    public HomePage(Page page) {
        this.page = page;
        this.nameInput = page.locator("input[name=\"search\"]");
    }

    public void loginToWiki() {
        page.navigate(EnvironmentVariableReader.getProperties("URL"));
        nameInput.click();
        nameInput.fill("playwright");
        nameInput.press("Enter");
    }

}
