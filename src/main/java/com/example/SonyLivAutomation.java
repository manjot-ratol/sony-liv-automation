package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SonyLivAutomation {
    public static void main(String[] args) {
        SafariOptions options = new SafariOptions();
        WebDriver driver1 = null;
        WebDriver driver2 = null;

        try {

            // Initialize WebDriver for Chrome
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            ChromeOptions chromeOptions = new ChromeOptions();

            // Initialize SafariDriver
            driver1 = new SafariDriver(options);
            driver1.manage().window().maximize(); // Make Safari full screen

            // Function to play video and handle mute
            playVideo(driver1);

            // Close the existing Safari session
            if (driver1 != null) {
                driver1.quit();
                driver1 = null; // Ensure no lingering references
            }

            // Open a new Safari session
            driver1 = new SafariDriver(options);
            driver1.manage().window().maximize(); // Make Safari full screen

            // Function to play video
            playVideo(driver1);
            Thread.sleep(40000);
            boolean d1 = false;
            // Loop or additional actions
            while (true) {
                if (d1) {

                    // Close the existing Safari session
                    if (driver1 != null) {
                        driver1.quit();
                        driver1 = null; // Ensure no lingering references
                    }

                    // Open a new Safari session
                    driver1 = new SafariDriver(options);
                    driver1.manage().window().minimize(); // Make Safari minimize

                    // Function to play video
                    playVideo(driver1);
                    muteVideo(driver2);

                    // let the advertisment pass before stoping other window
                    Thread.sleep(20000);
                    driver1.manage().window().maximize();
                    unmuteVideo(driver2);
                    if (driver2 != null) {
                        driver2.quit();
                        driver2 = null; // Ensure no lingering references
                    }
                    Thread.sleep(40000);
                    d1 = false;
                } else {

                    // Close the existing chrome session
                    if (driver2 != null) {
                        driver2.quit();
                        driver2 = null; // Ensure no lingering references
                    }

                    // Open a new chrome session
                    driver2 =  new ChromeDriver(chromeOptions);
                    driver2.manage().window().minimize(); // Make Chrome minimize

                    // Function to play video and handle mute
                    playVideo(driver2);
                    muteVideo(driver2);

                    // let the advertisment pass before stoping other window
                    Thread.sleep(20000);
                    driver2.manage().window().maximize(); // Make Chrome full screen
                    unmuteVideo(driver2);
                    if (driver1 != null) {
                        driver1.quit();
                        driver1 = null; // Ensure no lingering references
                    }
                    Thread.sleep(40000);
                    d1 = true;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Ensure the driver is closed
            if (driver1 != null) {
                driver1.quit();
            }
            if (driver2 != null) {
                driver2.quit();
            }
        }
    }

    private static void playVideo(WebDriver driver) throws InterruptedException {
        driver.get("https://www.sonyliv.com");
        Thread.sleep(5000); // Wait for the page to load

        // Find and click the 'Play Now' button
        WebElement playButton = driver.findElement(By.xpath(
                "//*[@id=\"homePage\"]/div/div/div/div[1]/div[2]/div/div/div[2]/div/div[1]/div/div/div/div[3]/div[2]/button"));
        playButton.click();
    }

    private static void muteVideo(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("var videos = document.querySelectorAll('video'); for (var i = 0; i < videos.length; i++) { videos[i].muted = true; }");
    }

    private static void unmuteVideo(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("var videos = document.querySelectorAll('video'); for (var i = 0; i < videos.length; i++) { videos[i].muted = false; }");
    }
}
