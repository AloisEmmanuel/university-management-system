@echo off
REM Compile script for University Management System (Windows)

echo =====================================
echo Compiling University Management System
echo =====================================

REM Create bin directory if it doesn't exist
if not exist "bin" (
    echo Creating bin directory...
    mkdir bin
)

REM Create a temporary file with all .java files
echo Compiling Java files...
dir /s /B src\*.java > sources.txt

REM Compile using the file list
javac -d bin @sources.txt

REM Delete the temporary file
del sources.txt

REM Check if compilation was successful
if %ERRORLEVEL% EQU 0 (
    echo.
    echo Compilation successful!
    echo.
    echo To run the application, use:
    echo   run.bat
    echo Or manually:
    echo   java -cp bin com.university.Main
) else (
    echo.
    echo Compilation failed! Please check the error messages above.
    exit /b 1
)

pause