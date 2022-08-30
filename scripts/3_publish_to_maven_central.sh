set -e

newVersion=$1
oldVersion=$2
jiraTicketNumber=$3
isDryRun=$4

if [[ $isDryRun = 'y' ]]
then
  masterBranch=master-dry-run
else
  masterBranch=master
fi

echo "-------> Checking out master"
git checkout $masterBranch
git fetch
git pull

echo "-------> Publishing to maven central"
./gradlew publish
printf "\n\n"
echo "-------> NEXT: Continue the release on Sonatype web page"
echo "-------> Press enter to open the browser... "
read -r
open https://repo1.maven.org/maven2/com/tink/core
open https://oss.sonatype.org

echo "-------> Polling for release to be complete"
until curl -s -f -o /dev/null "https://repo1.maven.org/maven2/com/tink/core/$newVersion/"
do
  echo "-------> Polling: publishing on Maven central not yet completed, please wait... "
  sleep 5
done

echo "\007"
clear
echo "-------> DONE: Publishing to Maven Central!"
echo "-------> Press enter to launch the script #4 to create a Github release on the public repo"
./scripts/4_github_release.sh "$newVersion" "$oldVersion" "$jiraTicketNumber" "$isDryRun"