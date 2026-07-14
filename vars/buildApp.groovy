def call_Iam_sharedLib() {
    echo "Building Application"

    sh 'mvn clean package -DskipTests' 

    echo "Build Completed"
}
