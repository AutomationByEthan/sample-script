// Sample Katalon Studio script for Elgin Automation
// Purpose: Demonstrate clean coding and robust XPath design for a simple login/logout automation
// Note: This is a simplified example. Contact us for advanced features.

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.Keys as Keys

// Open browser and navigate to Todoist
WebUI.openBrowser('https://app.todoist.com')
WebUI.setViewPortSize(1920, 1080)
WebUI.delay(3)

// Define robust XPath for username field
TestObject usernameField = new TestObject('Username Field')
usernameField.addProperty('xpath', ConditionType.EQUALS, '//input[@type="email"]')
WebUI.setText(usernameField, 'testuser@example.com')

// Define robust XPath for password field
TestObject passwordField = new TestObject('Password Field')
passwordField.addProperty('xpath', ConditionType.EQUALS, '//input[@type="password"]')
WebUI.setEncryptedText(passwordField, 'encrypted-placeholder') // Placeholder for demo
WebUI.sendKeys(passwordField, Keys.chord(Keys.ENTER))
WebUI.waitForPageLoad(10)

// Capture screenshot for reporting
WebUI.takeScreenshot('LoginSuccess.jpg')

// Logout
TestObject accountDropdown = new TestObject('Account Dropdown')
accountDropdown.addProperty('xpath', ConditionType.EQUALS, '//button[@aria-label="Account menu"]')
WebUI.click(accountDropdown)
WebUI.delay(2)

TestObject logoutButton = new TestObject('Logout Button')
logoutButton.addProperty('xpath', ConditionType.EQUALS, '//div[@data-action-hint="logout"]')
WebUI.click(logoutButton)
WebUI.delay(2)

// Close browser
WebUI.closeBrowser()
