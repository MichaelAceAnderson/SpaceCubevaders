@echo off
cd /d %~dp0
setlocal enabledelayedexpansion

@REM Use the right Java version for compilation, set JAVA_HOME to the JDK path
set "JAVA_HOME=C:\Program Files\Java\jdk-15.0.2"
@REM if PATH does not already contain JAVA_HOME, add it
echo %PATH% | findstr /C:"%JAVA_HOME%\bin" >nul || (call :colorEcho "JAVA_HOME\bin not found in PATH, using a temporary one: %JAVA_HOME%" "Yellow" & set "PATH=%JAVA_HOME%\bin;%PATH%")
set "APP_NAME=SpaceCubevaders"
set "OUTPUT_DIR=bin"

@REM Use Java Compiler to compile the source code into .class files, including all JARs in the lib directory
call :colorEcho "Compiling source code..." "DarkBlue"
javac -d %OUTPUT_DIR% -sourcepath src -cp "lib\*" src\main\MainGL.java
if %errorlevel% neq 0 (
	call :colorEcho "Compilation failed (error code %errorlevel%)" "Red"
	pause
	exit /b %errorlevel%
)

@REM Create a manifest file to specify the main class for the JAR, then create the JAR with the manifest
call :colorEcho "Creating manifest file..." "DarkBlue"
echo Main-Class: main.MainGL > %OUTPUT_DIR%\manifest.txt
set "LIBRARIES="
for /f "delims=" %%f in ('dir /b lib\') do (
	call :colorEcho "Adding ..\lib\%%f to classpath in manifest..." "DarkBlue"
	set "LIBRARIES=!LIBRARIES! ..\lib\%%f"
)
call :colorEcho "Writing following libraries to manifest classpath: !LIBRARIES!" "DarkBlue"
echo Class-Path: !LIBRARIES! >> %OUTPUT_DIR%\manifest.txt
if %errorlevel% neq 0 (
	call :colorEcho "Manifest creation failed (error code %errorlevel%)" "Red"
	pause
	exit /b %errorlevel%
)
@REM If file already exists, delete it before creating a new one
if exist %OUTPUT_DIR%\%APP_NAME%.jar (
	call :colorEcho "Deleting existing JAR file..." "DarkBlue"
	del %OUTPUT_DIR%\%APP_NAME%.jar
	if %errorlevel% neq 0 (
		call :colorEcho "Failed to delete existing JAR file (error code %errorlevel%)" "Red"
		pause
		exit /b %errorlevel%
	)
)
call :colorEcho "Creating JAR file..." "DarkBlue"
jar cfm %OUTPUT_DIR%\%APP_NAME%.jar %OUTPUT_DIR%\manifest.txt -C %OUTPUT_DIR% .
if %errorlevel% neq 0 (
	call :colorEcho "JAR creation failed (error code %errorlevel%)" "Red"
	pause
	exit /b %errorlevel%
)

call :colorEcho "Compilation successful, %OUTPUT_DIR%\%APP_NAME%.jar created." "Green"
exit /b 0

@REM ============================================
@REM Affiche du texte en couleur
@REM Usage: call echoColor.bat "Message" "ForegroundColor" "BackgroundColor"
@REM Premier argument : le message Ö afficher
@REM Deuxiäme argument : la couleur du texte (ForegroundColor)
@REM Troisiäme argument (optionnel) : la couleur de fond (BackgroundColor), optionnel
@REM Colors: Black, DarkBlue, DarkGreen, DarkCyan, DarkRed, DarkMagenta, DarkYellow, Gray, DarkGray, Blue, Green, Cyan, Red, Magenta, Yellow, White
@REM ============================================
:colorEcho
setlocal
set "msg=%~1"
set "fg=%~2"
set "bg=%~3"
@REM Si le message est vide, erreur
if "%msg%"=="" (
	echo "Error: No message provided to colorEcho function." "Red"
	endlocal
	goto :eof
)
if "%fg%"=="" (
	echo "Error: No foreground color provided to colorEcho function." "Red"
	endlocal
	goto :eof
)
if "%bg%"=="" (
	powershell -Command "Write-Host '%msg%' -ForegroundColor %fg%"
) else (
	powershell -Command "Write-Host '%msg%' -ForegroundColor %fg% -BackgroundColor %bg%"
)
endlocal
goto :eof