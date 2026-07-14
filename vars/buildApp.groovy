def call()
{

echo "I am from shared lib"

sh 'mvn clean package -DskipTests'

}
