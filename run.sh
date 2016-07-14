
APK=$1
aapt list -a $APK | grep SdkVersion >> sdk.txt
java -jar converter.jar sdk.txt
chmod 777 drive.sh
./drive.sh  $APK targetClassName targetMethodName
