set -e

echo "--> Enter new version (x.y.z format):"
# shellcheck disable=SC2162
read newVersion

# TODO: read version from file
if [[ $newVersion =~ ^([0-9]{1,2}\.){2}[0-9]{1,10}$ ]]; then
  echo "--> Version number: $newVersion is in the correct format"
else
  echo "--> $newVersion is not in the right format."
  exit
fi

git checkout master
git fetch
git pull

echo "--> Publishing to maven central"
#./gradlew publish
printf "\n\n"
echo "--> NEXT: Continue the release on Sonatype web page"
echo "--> Press enter to open the browser... "
read -r
open https://repo1.maven.org/maven2/com/tink/core
open https://oss.sonatype.org

echo "--> Polling for release to be complete"
until curl -s -f -o /dev/null "https://repo1.maven.org/maven2/com/tink/core/$newVersion/"
do
  echo "--> Polling: publishing on Maven central not yet completed, please wait... "
  sleep 5
done

echo "\007"
echo "--> DONE: Publishing to Maven Central!"
echo "--> NEXT: Launch the script for creating a release on Github"