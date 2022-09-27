# NTP AT - Automation Tool

![JAVA](https://img.shields.io/badge/language-java-critical?style=flat-square)


Setup Guide
-

* Download the appropriate web driver (Should be compatible with the testing browser's version) and place it in
  **webDrivers** folder in root directory

Development Guide
-
* This UI automation tool is based on **BDD** and due to the maintainability and the nature of DFN-NTP solutions, for
  master data we have established a dependency between scenarios. You can write your scenarios in a fully isolated manner also.
* Use Spring to manage dependencies as the customization model is depend on that.
* Use **IncludedTags** and **ExcludedTags** classes to include or exclude specific tags from the test runners.
* Only xml runners are configured to run in parallel mode, and they will send email after the test execution.


How to run tests
-
Core

* Run from feature file
* Run from customized runners
* Run from xml runners

Country / Broker

* Avoid run from feature file directly. Use Dev runners provided instead

