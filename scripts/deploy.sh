# scripts/before_install.sh /bin/bash

cd /home/ubuntu/app
# 기존 프로세스 종료
pid=$(sudo lsof -t -i:8080 || true)
if [ ! -z "$pid" ]; then
  echo "Killing process $pid"
  sudo kill -15 $pid
  sleep 5
fi

# scripts/start_application.sh
#!/bin/bash
cd /home/ubuntu/app
echo "${APPLICATION_PROPERTIES}" > src/main/resources/application.yml
./gradlew clean build
nohup java -jar -Duser.timezone=Asia/Seoul build/libs/*SNAPSHOT.jar > ./output.log 2>&1 &

# scripts/health_check.sh
#!/bin/bash
sleep 10
if ! sudo lsof -i:8080; then
  echo "Application failed to start"
  exit 1
fi
echo "Application successfully started"