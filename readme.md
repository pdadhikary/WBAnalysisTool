# WB Analysis Tool

Java application to analyze World Bank data.

### Contributors

- [Deepta Adhikary](https://github.com/pdadhikary/)
- [Hakam Singh](https://github.com/Hakam-singh)
- Adil Garad
- Abdullah Ali

## Introduction

This tool uses the
[World Bank API](https://datacatalog.worldbank.org/home)
to load key health and development indicators.
The user can use this data to perform various predefined
analyses. They can also visualize the result of these
analyses via plots and reports using
[JFreeChart](https://www.jfree.org/jfreechart/).

## How to Run the App

With Maven installed on your system, `cd` to the app
directory the app directory and run `mvm clean install`
this will install all the dependencies for the project
and run the test suite.

- The `App.java` file contains an example of the login
  GUI and app GUI.
- The `AnalysisStrategyExample.java` showcases the
  strategies as standalone classes and prints out the result.

## Project Structure and Design Patterns

The backbone of the project uses a classic MVC composite
pattern.
<br>
Since the World Bank API presents the data in JSON
format, there is an inherent recursive pattern, we
decided to use the decorator pattern to build the data
fetchers. To simply the user interface of the data fetcher
for clients using the package, we also added a static
Factory method.
