def call_Iam_sharedLib() {
    echo "Building Application"

    sh 'mvn clean package'

    echo "Build Completed"
}
