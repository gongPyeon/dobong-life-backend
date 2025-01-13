#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/app
PORT=8080

# 디버깅을 위한 정보 출력
echo "Current directory before cd: $(pwd)"
echo "Current user: $(whoami)"
echo "Environment variables:"
env

echo "> 현재 구동 중인 애플리케이션 pid 확인"
CURRENT_PID=$(sudo lsof -t -i:$PORT)

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -9 $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 5
fi

echo "> 새 애플리케이션 배포"

cd $REPOSITORY
echo "Current directory after cd: $(pwd)"
echo "Directory contents:"
ls -l

JAR_NAME=$(ls -tr *SNAPSHOT.jar | tail -n 1)

echo "> JAR NAME: $JAR_NAME"
echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar \
    -Dspring.config.location=classpath:/application.yml,/home/ubuntu/app/src/main/resources/application.yml \
    -Duser.timezone=Asia/Seoul \
    $JAR_NAME >> $REPOSITORY/nohup.out 2>&1 &