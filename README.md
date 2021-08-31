# Take Home Test from Miro

Test automation skills check.

To develop e2e tests that verify registration in the Miro service. All possible positive and negative cases. (only on
the page https://miro.com/signup/ )

Note: Please consider the screen “Check your email” as the trigger of success registration.

- Tests requirements:
- It would be better to develop tests in Java
- Tests should be developed using a Selenium WebDriver.
- The test project should have a clear and detailed README file

---

# Solution Overview

## Approach

In my opinion, end-to-end tests should be used wisely and sparingly. By wisely and sparingly, I mean choose a set of
test cases or flows that are critical to the business or product operation, or have the potential of significantly
reducing manual regression testing.

The reason being is that end-to-end tests are, in most cases, expensive to develop and hard to maintain.

Therefore, if we
use [the agile test automation pyramid](https://searchitoperations.techtarget.com/definition/agile-test-automation-pyramid)
, we should consider increasing unit tests during the implementation phase, identify integration points within a
development and ensure that we have proper integration tests covering them, and last but not least, make sure that we
identify which end-to-end flows we may want to automate.

## Automated Test Cases

I chose to automate a small set of test cases that I consider critical to business (open for debate) to demonstrate how
I would structure an automation project effort.

1. Error messages are displayed for empty submissions of required fields ✔
1. The regular (work email) sign up flow is working as expected ✔
1. The social sign up flow (i.e. Google, Slack, ...) are working as expected ✔
1. The "Terms" and "Privacy Policy" links are working as expected ✘ (Not implemented due to time constraints)

## How to Run The Solution

1. Clone the repository and `cd` to it
   ```
   git clone https://github.com/haithambb/Miro.git 
   cd Miro
   ```
1. Install Gradle Build Tool
   ```
   brew install gradle
   ```
1. Download Chrome Driver and place it in root dir of project i.e. `Miro`<br />
   **Note:** At time of test development, I was using Chrome version 92, so I downloaded `chromedriver_mac64.zip`
   from [this link](https://chromedriver.storage.googleapis.com/index.html?path=92.0.4515.107/), unzipped it and
   placed `chromedriver` executable in project's directory.<br />
   ```
   hbayoumi@JPN Miro % ls -lrt
   total 16356
   -rwxr-xr-x 1 hbayoumi staff 16739600 Jul 18 11:45 chromedriver
   drwxr-xr-x 5 hbayoumi wheel      160 Aug 30 15:52 src
   -rw-r--r-- 1 hbayoumi wheel       27 Aug 30 15:53 settings.gradle
   -rw-r--r-- 1 hbayoumi wheel      464 Aug 30 16:02 build.gradle
   ```
1. Run the following to execute tests
   ```
   hbayoumi@JPN Miro % gradle test
   ```
   The output should look something like this
   ```
   BUILD SUCCESSFUL in 564ms
   2 actionable tasks: 2 up-to-date
   ```
1. Open the following report (relative to project's dir) to view test results
   ```
   open ./build/reports/tests/test/index.html
   ```
