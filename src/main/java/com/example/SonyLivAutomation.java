package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SonyLivAutomation {
    public static void main(String[] args) {
        SafariOptions options = new SafariOptions();
        WebDriver driver = null;

        try {
            // Initialize SafariDriver
            driver = new SafariDriver(options);
            driver.manage().window().maximize(); // Make Safari full screen
            
            // Function to play video and handle mute
            playVideo(driver);

            // Close the existing Safari session
            if (driver != null) {
                driver.quit();
                driver = null; // Ensure no lingering references
            }

            // Open a new Safari session
            driver = new SafariDriver(options);
            driver.manage().window().maximize(); // Make Safari full screen
            
            // Function to play video and handle mute
            playVideo(driver);

            // Loop or additional actions
            while (true) {
                // Wait for 30 seconds before opening a new tab
                Thread.sleep(30000);
                
                // Close the existing Safari session
                if (driver != null) {
                    driver.quit();
                    driver = null; // Ensure no lingering references
                }
                
                // Open a new Safari session
                driver = new SafariDriver(options);
                driver.manage().window().maximize(); // Make Safari full screen
                
                // Function to play video and handle mute
                playVideo(driver);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Ensure the driver is closed
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static void playVideo(WebDriver driver) throws InterruptedException {
        driver.get("https://www.sonyliv.com");
        Thread.sleep(5000); // Wait for the page to load

        // Find and click the 'Play Now' button
        WebElement playButton = driver.findElement(By.xpath("//*[@id=\"homePage\"]/div/div/div/div[1]/div[2]/div/div/div[2]/div/div[1]/div/div/div/div[3]/div[2]/button"));
        playButton.click();

        // Wait for 30 seconds
        Thread.sleep(30000);

        // Mute the video
        // JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        // jsExecutor.executeScript("document.querySelector('video').muted = true;");
        
        // Wait for 45 seconds
        Thread.sleep(30000);
        
        // Unmute the video
        // jsExecutor.executeScript("document.querySelector('video').muted = false;");
    }
}
