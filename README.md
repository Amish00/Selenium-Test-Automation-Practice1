# Selenium Web Automation Practice

A comprehensive Java-based test automation script designed to interact with complex web elements on the popular [Test Automation Practice Blog](https://testautomationpractice.blogspot.com/). 

This project serves as a practical implementation of **Selenium WebDriver 4**, demonstrating how to handle real-world UI automation challenges including dynamic data, complex mouse interactions, and browser window management.

## Tech Stack
* **Language:** Java
* **Automation Tool:** Selenium WebDriver 4.x
* **Build Tool:** Maven (or standard Java project)

## Features Automated
This script successfully automates and interacts with the following web elements:
* **Form Controls:** Text inputs, radio buttons, checkboxes, standard dropdowns, and multi-select dropdowns.
* **Date Pickers:** Bypassing complex UI calendars using JavaScript and direct input injections.
* **File Uploads:** Handling both single and multiple file uploads simultaneously.
* **Web Tables:** 
  * Extracting data from Static Tables.
  * Mapping relational data in Dynamic Tables (e.g., finding dynamic CPU load based on browser name).
  * Interacting with Checkboxes across **Pagination Tables**.
* **Alerts & Popups:** Handling Simple, Confirmation, and Prompt alerts.
* **Window/Tab Management:** Capturing window handles and switching focus between multiple browser tabs.
* **Advanced Mouse & Keyboard Interactions (Actions Class):**
  * Mouse Hovers (revealing hidden dropdowns)
  * Double-Clicking (triggering copy/paste events)
  * Drag and Drop functionality
  * Handling jQuery UI Sliders (using keyboard Arrow Key emulation for high reliability)

## Getting Started

### Prerequisites
* JDK 11 or higher installed.
* An IDE like IntelliJ IDEA or Eclipse.
* Google Chrome installed on your machine.
* *Note: Since this uses Selenium 4, ChromeDriver is managed automatically. You do not need to download `.exe` driver files manually.*

### Installation & Execution
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/yourusername/Selenium-Test-Automation-Practice.git](https://github.com/yourusername/Selenium-Test-Automation-Practice.git)
