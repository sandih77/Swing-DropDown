#!/bin/bash
clear

echo "🔄 Compilation en cours..."

if [ -d "classes" ]; then
    rm -r classes
fi

mkdir -p classes

if javac -d classes \
    src/candidat/*.java \
    src/component/*.java \
    src/election/*.java \
    src/entity/*.java \
    src/gui/*.java \
    src/listener/*.java \
    src/main/*.java; then
    
    echo "✅ Compilation réussie. Lancement de l'application..."
    java -cp classes main.Main
else
    echo "❌ Erreur lors de la compilation"
    exit 1
fi
