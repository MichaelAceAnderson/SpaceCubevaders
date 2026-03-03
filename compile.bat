@echo off
cd /d %~dp0
setlocal enabledelayedexpansion
@REM Use the right Java version for compilation, set JAVA_HOME to the JDK path
set "JAVA_HOME=C:\Program Files\Java\jdk-21"
@REM if PATH does not already contain JAVA_HOME, add it
echo %PATH% | findstr /C:"%JAVA_HOME%\bin" >nul || (echo Error: JAVA_HOME\bin not found in PATH, using a temporary one & set "PATH=%JAVA_HOME%\bin;%PATH%")
set "APP_NAME=SpaceCubevaders"
set "OUTPUT_DIR=bin"

@REM Use Java Compiler to compile the source code into .class files, including all JARs in the lib directory
echo Compiling source code...
javac -d %OUTPUT_DIR% -sourcepath src -cp "lib\*" src\main\MainGL.java
if %errorlevel% neq 0 (
	echo Compilation failed ^(error code %errorlevel%^)
	pause
	exit /b %errorlevel%
)

@REM Create a manifest file to specify the main class for the JAR, then create the JAR with the manifest
echo Creating manifest file...
echo Main-Class: main.MainGL > %OUTPUT_DIR%\manifest.txt
set "LIBRARIES="
for /f "delims=" %%f in ('dir /b lib\') do (
	echo Adding ..\lib\%%f to classpath in manifest...
	set "LIBRARIES=!LIBRARIES! ..\lib\%%f"
)
echo Writing following libraries to manifest classpath: !LIBRARIES!
echo Class-Path: !LIBRARIES! >> %OUTPUT_DIR%\manifest.txt
if %errorlevel% neq 0 (
	echo Manifest creation failed ^(error code %errorlevel%^)
	pause
	exit /b %errorlevel%
)
@REM If file already exists, delete it before creating a new one
if exist %OUTPUT_DIR%\%APP_NAME%.jar (
	echo Deleting existing JAR file...
	del %OUTPUT_DIR%\%APP_NAME%.jar
	if %errorlevel% neq 0 (
		echo Failed to delete existing JAR file ^(error code %errorlevel%^)
		pause
		exit /b %errorlevel%
	)
)
echo Creating JAR file...
jar cfm %OUTPUT_DIR%\%APP_NAME%.jar %OUTPUT_DIR%\manifest.txt -C %OUTPUT_DIR% .
if %errorlevel% neq 0 (
	echo JAR creation failed ^(error code %errorlevel%^)
	pause
	exit /b %errorlevel%
)

echo Compilation successful, %OUTPUT_DIR%\%APP_NAME%.jar created.
