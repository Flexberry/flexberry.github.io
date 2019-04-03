---
title: Check-list for Code Review
sidebar: flexberry-platform_sidebar
keywords: Design code, requirements for the execution of tasks, good code, documentation, testing
summary: List of requirements for task execution for the correction and finishing products platform Flexberry
toc: true
permalink: en/fp_code-review-check-list.html
lang: en
autotranslated: true
hash: 5f54a55da284fe972ff16bfb7ffbfdb647b291fbca137dec5b02830224cd0dd4
---

Check-list for CodeReview - a wonderful tool, allowing to check that reviewer do not forget to verify the absence of any potential problems with the test code.

## Aspects which need to check

### Code

* __The efficiency of the code.__
* Code works and performs its duties, the logic is correct, etc.
* Cycles there is a set length and correct termination conditions.
* __Code comprehension.__
* Code simple to understand.
* The method names are not too long.
* __The code conforms to the accepted style.__
* Properly named namespaces / classes / methods / variables.
* Followed the rules of naming files according to classes.
* Corresponds to the namespace of the class and the physical location of the files.
* In each file, only one class.
* __The redundancy of the code.__
* There are no duplicate parts which can finest a separate function.
* There are no methods which can be in the code to replace the library functions.
* There is no commented out code.
* There is no change of the code, intended for debugging.
* No global / static variables from which you can delete or move them.
* __Code independence.__
* Code is as independent as possible.
* __Upgrade configuration.__
* If necessary, changes in the configuration.
* There is a description of the changes to the release.
* Modified installation tools / deployment (for example, in a NuGet package).
* __Correct handling of exceptions.__
* Exceptions are used as intended.
* Stores information about the error (e.g., log)
* There are no empty catch blocks.
* Presents a clear explanation of what the error is.
* Messages are localized.
* __Provided security.__
* All input data are checked (for correct type, length, format, range).
* Used to check CodeContracts (.NET >= 4.0).
* All output is checked and, if necessary, coded (e.g., XSS).

### Source Control

* __The correct comments to the commit (check-in TFS).__
* Comment on the commit (check-in TFS) reflects updated accordingly.
* Version control system is used for its intended purpose (for example, does not have prompts of the form "not to forget to update ...", "TODO")?
* __Relevant comments adopted by the requirements.__
* Comments conform to requirements and rules (Russian) language.
* __Atomic commit.__
* NB: normally a commit is not atomic if in the description there is the word "and".

### Documentation

* __There are comments in the code.__
* Comments reflect the meaning of the code.
* All functions and their parameters are commented out.
* __Relevant comments adopted by the requirements.__
* Comments conform to requirements and rules (Russian) language.
* __Made appropriate notes in the WIKI.__
* Some unusual behavior or description of the edge cases are documented.
* The use and operation of third-party libraries is documented.
* All data structures and units described.

### Testing

* __Have tests for the code.__
* Tests have, and they are sufficient.
* Unit tests check that the code provides the functionality you require.
* Created test script and a special page on the corresponding test stand.
* Test script attached to the desired test plan.
* Test script complies with the requirements.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}