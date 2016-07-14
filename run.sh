
APK=$1
TARGET_CLASS=$2
TARGET_METHOD=$3
aapt list -a $APK | grep SdkVersion >> sdk.txt
java -jar converter.jar sdk.txt
chmod 777 drive.sh
./drive.sh  $APK $TARGET_CLASS $TARGET_METHOD
