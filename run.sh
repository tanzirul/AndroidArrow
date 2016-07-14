
APK="/Users/tanzirulazim/Dropbox/PhD/RollbackNRestart/data/recentdata11-24-14/Audalyzer-1.15.apk"
aapt list -a $APK | grep SdkVersion >> sdk.txt
java -jar converter.jar sdk.txt
chmod 777 drive.sh
./drive.sh  $APK Lorg/hermit/android/core/AppUtils$Version getAppVersion