--- 
title: Check-list for Code Review 
sidebar: flexberry-platform_sidebar 
keywords: 
toc: true 
permalink: en/fp_code-review-check-list.html 
lang: en 
autotranslated: true 
hash: 53d464fc3c2317d0c9f5ef5fde3b03072bf5d890e7ce8233ca2fc411b215c63e 
--- 

## checklist 

Check-list for CodeReview - a wonderful tool, allowing to check that reviewer do not forget to verify the absence of any potential problems with the test code. 

*Aspects which need to check* 

### Code 

* **The efficiency of the code.** 
* Code works and performs its duties, the logic is correct, etc. 
* Cycles there is a set length and correct termination conditions. 
* **The clarity of code.** 
* Code simple to understand. 
* The method names are not too long. 
* **The code conforms to the accepted style.** 
* Properly named namespaces / classes / methods / variables. 
* Followed the rules of naming files according to classes. 
* Corresponds to the namespace of the class and the physical location of the files. 
* In each file, only one class. 
* **The redundancy of the code.** 
* There are no duplicate parts which can finest a separate function. 
* There are no methods which can be in the code to replace the library functions. 
* There is no commented out code. 
* There is no change of the code, intended for debugging. 
* No global / static variables from which you can delete or move them. 
* **The independence of the code.** 
* Code is as independent as possible. 
* **Updates configuration.** 
* If necessary, changes in the configuration. 
* There is a description of the changes to the release. 
* Modified installation tools / deployment (for example, in a NuGet package). 
* **Correct handling of exceptions.** 
* Exceptions are used as intended. 
* Stores information about the error (e.g., log) 
* There are no empty catch blocks. 
* Presents a clear explanation of what the error is. 
* Messages are localized. 
* **Provided security.** 
* All input data are checked (for correct type, length, format, range). 
* Used to check CodeContracts (.NET >= 4.0). 
* All output is checked and, if necessary, coded (e.g., XSS). 

### Source Control 

* **The correct comments to the commit (check-in TFS).** 
* Comment on the commit (check-in TFS) reflects updated accordingly. 
* Version control system is used for its intended purpose (for example, does not have prompts of the form "not to forget to update ...", "TODO")? 
* **The review accepted requirements.** 
* Comments conform to requirements and rules (Russian) language. 
* **The atomicity of the commit.** 
* NB: normally a commit is not atomic if in the description there is the word "and".

### Documentation 

* **Have comments in the code.** 
* Comments reflect the meaning of the code. 
* All functions and their parameters are commented out. 
* **The review accepted requirements.** 
* Comments conform to requirements and rules (Russian) language. 
* **Made relevant notes in the WIKI.** 
* Some unusual behavior or description of the edge cases are documented. 
* The use and operation of third-party libraries is documented. 
* All data structures and units described. 

### Testing 

* **There are tests for the code.** 
* Tests have, and they are sufficient. 
* Unit tests check that the code provides the functionality you require. 
* Created test script and a special page on the corresponding test stand. 
* Test script attached to the desired test plan. 
* Test script complies with the requirements. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/