ANDROID_VER="android-19"
REDEXER_PATH=$REDEXER_HOME
GATOR_PATH=$GATOR_HOME
adk_path=$ANDROID_SDK



filename=$(basename "$1")
APK_FILE="${filename%.*}"
APK_PATH=$1
OUTPUT_REDEXER=$HOME/Dropbox/PhD/RollbackNRestart/code/$APK_FILE"_redexer.txt"
CLASSNAME=$2
METHODNAME=$3
totalstring=$2";->"$3

# filename=$(basename "$1")
# extension="${filename##*.}"
# fnwext="${filename%.*}"

cd $REDEXER_PATH
echo $totalstring > data/directed.txt
echo ">>>>>>new data:>>>>>>" >> $OUTPUT_REDEXER
ruby scripts/cmd.rb $1 --cmd directed >> $OUTPUT_REDEXER

#You can specify target methods to be invoked in data/directed.txt
cd $GATOR_PATH
cd apk-preprocess-tool/scripts/
./convert.sh $1

# The output of this preprocessing is a directory under the
# apk-preprocess-tool directory, with name "[apk file base
# name]forEclipseProject". The directory structure is consistent with
# the project structure used by Eclipse ADT, and can be used as input to
# the guiAnalysis.sh script. The directory can then be renamed and moved
# to AndroidBench.
APK_PROJECT=$GATOR_PATH/apk-preprocess-tool/output/$APK_FILE"forEclipseProject"
echo $APK_PROJECT
cd $GATOR_PATH
SootAndroid/scripts/guiAnalysis.sh $GATOR_PATH/AndroidBench $adk_path $APK_PROJECT $ANDROID_VER $APK_FILE
cd .
mv $HOME/gui_transition.xml  $APK_FILE"_gator.xml"  
      
# SootAndroidOptions="-client GUIHierarchyPrinterClient"
