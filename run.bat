@echo off
cls

echo Compilation en cours...

REM Supprimer le dossier "classes" s'il existe
if exist classes (
    rmdir /s /q classes
)

REM Compiler les fichiers Java
javac -d classes src\*.java

REM Vérifier si la compilation a réussi
if %errorlevel% equ 0 (
    java -cp classes main.Main
) else (
    echo Erreur lors de la compilation
)
