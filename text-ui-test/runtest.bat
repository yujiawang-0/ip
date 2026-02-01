@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac -Xlint:none -d ..\bin ^
  ..\src\main\java\prime\core\*.java ^
  ..\src\main\java\prime\ui\*.java ^
  ..\src\main\java\prime\parser\*.java ^
  ..\src\main\java\prime\storage\*.java ^
  ..\src\main\java\prime\task\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM delete previous saved data file if it exists
if exist ..\text-ui-test\data\prime.txt del ..\text-ui-test\data\prime.txt

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin prime.core.Prime < correctInput.txt > CORRECT_ACTUAL.TXT

REM delete previous saved data file if it exists
if exist ..\text-ui-test\data\prime.txt del ..\text-ui-test\data\prime.txt

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin prime.core.Prime < wrongInput.txt > WRONG_ACTUAL.TXT

REM compare the output to the expected output
C:\Windows\System32\fc.exe CORRECT_ACTUAL.TXT CORRECT_EXPECTED.TXT || exit /b 1

C:\Windows\System32\fc.exe WRONG_ACTUAL.TXT WRONG_EXPECTED.TXT || exit /b 1
