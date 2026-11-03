@echo off
set /a i=1
:loop
if %i% leq 10 (
    echo %i%
    set /a i+=1
    timeout /t 1 /nobreak >nul
    goto loop
)