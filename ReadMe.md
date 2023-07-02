recommended to create a new BrowserContext for each test

trace viewer- https://trace.playwright.dev/

Accessibility testing

api testing

Cookies and local storage state can be used across different browsers. They depend on your 
application's authentication model: some apps might require both cookies and local storage.

install/upfdate browser
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install webkit"