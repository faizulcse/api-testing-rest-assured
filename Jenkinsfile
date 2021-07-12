node('master') {
    def TAGS = env.CUCUMBER_TAGS
    def WORKSPACE = env.WORKSPACE
    def BUILD_NUMBER = env.BUILD_NUMBER
    def JOB_NAME = env.JOB_NAME.toLowerCase()

    def DOCKER_IMAGE = "${JOB_NAME}-${BUILD_NUMBER}-image"
    def DOCKER_CONTAINER = "${JOB_NAME}-${BUILD_NUMBER}-container"
    def REPORTS_LOCATION = "${WORKSPACE}/reports"
    def LOGS_LOCATION = "${WORKSPACE}/logs"

    stage("Checkout Repository") {
        checkout scm
    }

    stage("Build Docker Image") {
        sh "docker build --tag ${DOCKER_IMAGE} --file '${WORKSPACE}/Dockerfile' '${WORKSPACE}' "
    }

    stage("Run Tests") {
        def exitCode = sh script: "docker run -t --name ${DOCKER_CONTAINER} ${DOCKER_IMAGE} mvn clean install -Dcucumber.options='--tags ${TAGS}' -Dbuild.number=${BUILD_NUMBER}", returnStatus: true
        if (exitCode == 1)
            currentBuild.result = "UNSTABLE"

    }

    stage("Reports Generate") {
        sh "mkdir ${REPORTS_LOCATION}"
        sh "mkdir ${LOGS_LOCATION}"
        sh "docker cp ${DOCKER_CONTAINER}:/app/target/cucumber-reports/. ${REPORTS_LOCATION}"
        sh "docker cp ${DOCKER_CONTAINER}:/app/logs/. ${LOGS_LOCATION}"
    }

    stage("Delete Docker Image & Container") {
        sh "docker rm  ${DOCKER_CONTAINER}"
        sh "docker rmi ${DOCKER_IMAGE} -f"
    }

    stage("Reports Publish") {
        publishHTML([
                allowMissing         : false,
                alwaysLinkToLastBuild: true,
                keepAll              : true,
                reportDir            : "${REPORTS_LOCATION}/cucumber-html-reports",
                reportFiles          : 'overview-features.html',
                reportName           : 'HTML Report',
                reportTitles         : ''])
    }
}
