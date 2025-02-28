#!/bin/bash

log() {
    echo "[INFO] $(date +'%Y-%m-%d %H:%M:%S') - $1"
}


log "Переход в корневую директорию проекта"
cd "$(dirname "$0")/.." || exit 1

log "Запуск докера"
docker-compose -f docker-compose-backend.yml up -d --build

log "Начало сборки проекта"
mvn clean package


log "Поиск JAR-файла"
JAR_FILE=$(ls target/*.jar | head -n 1)

if [[ -z "$JAR_FILE" ]]; then
    log "Ошибка: JAR-файл не найден!"
    exit 1
fi

log "Запуск JAR-файла: $JAR_FILE"
java -jar "$JAR_FILE"
