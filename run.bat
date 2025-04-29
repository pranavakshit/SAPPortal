@echo off
cd /d "%~dp0"

echo === Compiling the project ===
mvn clean compile
if %errorlevel% neq 0 (
    echo [ERROR] Compilation failed. Press any key to exit.
    pause > nul
    exit /b %errorlevel%
)

echo === Running the application ===
mvn exec:java -Dexec.mainClass="com.pranav.sportal.gui.MainPanel"
echo.
echo === If the window above disappears, press any key to see logs ===
pause
