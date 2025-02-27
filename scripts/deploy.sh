#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/app

echo "> 이전 배포 파일 정리"
find /home/ubuntu/app -name "*SNAPSHOT.jar" | sort -r | tail -n +3 | xargs -r rm

echo "> 현재 구동 중인 애플리케이션 pid 확인"
PORT=8080
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

# 전체 경로를 포함한 JAR 파일 찾기
JAR_NAME=$(ls -tr $REPOSITORY/*SNAPSHOT.jar | tail -n 1)

echo "> JAR NAME: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

# 기존 작동하는 방식과 동일하게 설정
nohup java -jar -Duser.timezone=Asia/Seoul $JAR_NAME >> $REPOSITORY/nohup.out 2>&1 &

# 실행 확인
sleep 5
echo "> 실행 로그 확인"
tail -n 20 $REPOSITORY/nohup.out