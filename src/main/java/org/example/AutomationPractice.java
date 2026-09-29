package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class AutomationPractice {
    public static void main(String[] args) throws InterruptedException {

        // 1. Initialize WebDriver (Selenium 4 built-in manager handles the setup)
        WebDriver driver = new ChromeDriver();

        // Implicit wait allows elements up to 10 seconds to load before throwing an error
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Navigate to the website
            driver.get("https://testautomationpractice.blogspot.com/");
            System.out.println("Website opened successfully.");

            Actions actions = new Actions(driver);

            // ==========================================
            // 1. DATA ENTRY FORM
            // ==========================================
            System.out.println("Filling Data Entry Form...");
            driver.findElement(By.id("name")).sendKeys("John Doe1");
            driver.findElement(By.id("email")).sendKeys("john.doe@example.com");
            driver.findElement(By.id("phone")).sendKeys("1234567890");
            driver.findElement(By.id("textarea")).sendKeys("123 Main St, Tech City");

            // Select Gender (Radio Button)
            driver.findElement(By.id("male")).click();

            // Select Days (Checkboxes)
            driver.findElement(By.id("monday")).click();
            driver.findElement(By.id("friday")).click();

            // Select Country from Dropdown
            WebElement countryElement = driver.findElement(By.id("country"));
            Select countryDropdown = new Select(countryElement);
            countryDropdown.selectByVisibleText("United States");

            // Select Colors Dropdown
            WebElement colorsElement = driver.findElement(By.id("colors"));
            Select colorsDropdown = new Select(colorsElement);
            colorsDropdown.selectByVisibleText("Blue");
            colorsDropdown.selectByVisibleText("Green");

            // Select Options Sorted List
            WebElement sortedListElement = driver.findElement(By.id("animals"));
            Select sortedListDropdown = new Select(sortedListElement);
            sortedListDropdown.selectByVisibleText("Elephant");


            //Date Pickers
            // Date Picker 1 (mm/dd/yyyy) - direct sendKeys
            driver.findElement(By.id("datepicker")).sendKeys("09/29/2026");

            // Date Picker 2 (dd/mm/yyyy) - direct sendKeys
            driver.findElement(By.id("txtDate")).click();
            driver.findElement(By.linkText("30")).click();

            // Date Picker 3 (Date Range)
            driver.findElement(By.id("start-date")).sendKeys("09/29/2026");
            driver.findElement(By.id("end-date")).sendKeys("10/15/2026");

            // Click the Submit button associated with the form/dates
            driver.findElement(By.xpath("//button[text()='Submit']")).click();
            System.out.println("Form Submitted.");


            // ==========================================
            // 2. UPLOAD FILES
            // ==========================================
            System.out.println("Uploading File...");
            //Single File Upload
            WebElement fileUpload = driver.findElement(By.id("singleFileInput"));
            fileUpload.sendKeys("C:\\Users\\AmishJoshi\\Downloads\\aa.png");

            //Multiple Files Upload
            WebElement multipleFileUpload = driver.findElement(By.id("multipleFilesInput"));

            String filePaths = "C:\\Users\\AmishJoshi\\Downloads\\aa.png\nC:\\Users\\AmishJoshi\\Downloads\\ss.png";
            multipleFileUpload.sendKeys(filePaths);



            // ==========================================
            // 3. STATIC WEB TABLE
            // ==========================================
            System.out.println("Reading Static Web Table...");
            // Example: Find the price of the book "Learn Java" using XPath
            String bookPrice = driver.findElement(By.xpath("//table[@name='BookTable']//td[text()='Learn Java']/following-sibling::td[2]")).getText();
            System.out.println("The price of the 'Learn Java' book is: " + bookPrice);


            // ==========================================
            // 4. DYNAMIC WEB TABLE
            // ==========================================
            System.out.println("Reading Dynamic Web Table...");

            String chromeCPU = driver.findElement(By.xpath("//table[contains(., 'Firefox')]//td[text()='Chrome']/following-sibling::td[4]")).getText();
            System.out.println("Current Chrome CPU Load is: " + chromeCPU);


            // ==========================================
            // 5. PAGINATION WEB TABLE
            // ==========================================
            System.out.println("Handling Pagination...");
            // Loop through the first 3 pages
            for (int page = 1; page <= 3; page++) {
                // Click on the specific page number
                WebElement pageLink = driver.findElement(By.xpath("//ul[@id='pagination' or @class='pagination']//a[text()='" + page + "']"));
                pageLink.click();
                Thread.sleep(1000); // Brief pause to allow the table to refresh

                // Select all product checkboxes on the current page
                List<WebElement> checkboxes = driver.findElements(By.xpath("//table[@id='productTable']//input[@type='checkbox']"));
                for (WebElement checkbox : checkboxes) {
                    if (!checkbox.isSelected()) {
                        checkbox.click();
                    }
                }
                System.out.println("Selected all items on page " + page);
            }


            // Dynamic Button (Start / Stop)
            System.out.println("Clicking Dynamic Button...");
            WebElement dynamicBtn = driver.findElement(By.xpath("//button[normalize-space()='START' or normalize-space()='STOP']"));
            dynamicBtn.click();

            Thread.sleep(1000);

            //Alerts & Popups
            System.out.println("Handling Alerts & Popups...");
            // a) Simple Alert
            driver.findElement(By.xpath("//button[normalize-space()='Simple Alert']")).click();
            Alert simpleAlert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Simple Alert says: " + simpleAlert.getText());
            simpleAlert.accept(); // Clicks "OK"

            // b) Confirmation Alert
            driver.findElement(By.xpath("//button[normalize-space()='Confirmation Alert']")).click();
            Alert confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
            confirmAlert.dismiss(); // Clicks "Cancel"

            // c) Prompt Alert
            driver.findElement(By.xpath("//button[normalize-space()='Prompt Alert']")).click();
            Alert promptAlert = wait.until(ExpectedConditions.alertIsPresent());
            promptAlert.sendKeys("Test Automation User"); // Types into the alert box
            promptAlert.accept();

            // Tabs
            System.out.println("Handling New Tabs...");
            String originalWindow = driver.getWindowHandle();

            driver.findElement(By.xpath("//button[normalize-space()='New Tab']")).click();

            // Loop through active windows to find the new one
            Set<String> allWindows = driver.getWindowHandles();
            for (String windowHandle : allWindows) {
                if (!originalWindow.contentEquals(windowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            System.out.println("Switched to Tab with title: " + driver.getTitle());
            driver.close(); // Close the new tab
            driver.switchTo().window(originalWindow); // Switch back to the main page

            //Mouse Hover
            System.out.println("Performing Mouse Hover...");
            WebElement pointMeMenu = driver.findElement(By.xpath("//button[normalize-space()='Point Me']"));

            // 1. Scroll the element into the center of the view to ensure nothing is blocking it
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", pointMeMenu);
            Thread.sleep(500); // brief pause to allow the scroll to finish

            // 2. Perform the hover action
            actions.moveToElement(pointMeMenu).perform();

            // 3. Use an Explicit Wait to wait for the specific dropdown link to become clickable.
            // Notice the updated XPath: It looks specifically for the div immediately following the "Point Me" button.
            WebElement laptopsLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Point Me']/following-sibling::div//a[normalize-space()='Laptops']")
            ));

            laptopsLink.click();
            System.out.println("Successfully clicked Laptops from the hover menu!");

            //Double Click
            System.out.println("Performing Double Click...");
            WebElement field1 = driver.findElement(By.id("field1"));

            // Clear default text and type our own test string
            field1.clear();
            field1.sendKeys("Selenium is awesome!");

            WebElement copyButton = driver.findElement(By.xpath("//button[normalize-space()='Copy Text']"));
            // Perform the double click
            actions.doubleClick(copyButton).perform();

            // Verify the text was copied into Field 2
            WebElement field2 = driver.findElement(By.id("field2"));
            System.out.println("Field 2 now contains: " + field2.getAttribute("value"));

            //Drag and Drop
            System.out.println("Performing Drag and Drop...");
            WebElement sourceElement = driver.findElement(By.id("draggable"));
            WebElement targetElement = driver.findElement(By.id("droppable"));

            // Click the source, hold, move to target, and release
            actions.dragAndDrop(sourceElement, targetElement).perform();
            System.out.println("Target text changed to: " + targetElement.getText());

            //Slider
            System.out.println("Moving the Slider...");
            // Grab the left sliding handle
            WebElement minSlider = driver.findElement(By.xpath("//div[@id='slider-range']/span[1]"));

            minSlider.click();
            for (int i = 0; i < 25; i++) {
                minSlider.sendKeys(Keys.ARROW_RIGHT);
            }

            System.out.println("All tasks completed successfully!");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Pause to let you see the final state before closing
            Thread.sleep(3000);
            // Quit the browser and end session
            driver.quit();
        }
    }
}