pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh '''
                    chmod +x gradlew
                    ./gradlew build -x test
                '''
            }
        }
        stage('DockerSize') {
            steps {
                sh '''
                    docker stop orders || true
                    docker rm orders || true
                    docker rmi orders || true
                    docker build -t orders .
                    echo "orders: build success"
                '''
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                docker run -e DAILY_SETTLEMENT_LAUNCHER_ZONE="${DAILY_SETTLEMENT_LAUNCHER_ZONE}" -e DAILY_SETTLEMENT_LAUNCHER_CRON="${DAILY_SETTLEMENT_LAUNCHER_CRON}" -e BOOTSTRAP_SERVERS="${BOOTSTRAP_SERVERS}" -e EUREKA_URL="${EUREKA_URL}" -e MASTER_DB_URL="${MASTER_DB_URL}/orders" -e MASTER_DB_USERNAME="${MASTER_DB_USERNAME}" -e MASTER_DB_PASSWORD="${MASTER_DB_PASSWORD}" -d --name orders --network gentledog orders
                echo "orders: run success"
                '''
                }
        }
    }
}