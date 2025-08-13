# Tutorial Cucumber Java

https://github.com/mbachmann/cucumber-java-tutorial

The solution per task is in the corresponding branch.

* **task01:** Cucumber project setup
* **task02:** Implement the login feature
* **task03:** Implement advanced Cucumber subjects

## Error on MacOS

If you get an error like this:

```java.lang.IllegalStateException: The driver executable does not exist: /Users/mbachmann/Downloads/chromedriver
    NoSuchDriver Unable to obtain: chromedriver, error chromedriver must be executable: src/test/resources/drivers/macos/chromedriver-138
```

Make sure that the chromedriver is executable:

```bash
chmod +x src/test/resources/drivers/macos/chromedriver-138
```



