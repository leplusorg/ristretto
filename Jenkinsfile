/*
 * Copyright 2016-present Thomas Leplus
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
pipeline {
    agent {
        docker {
            image 'maven:3-jdk-8'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh './mvnw --batch-mode --errors --fail-at-end --show-version -DinstallAtEnd=true -DdeployAtEnd=true verify'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}