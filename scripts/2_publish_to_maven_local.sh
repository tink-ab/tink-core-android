set -e

echo "-->Enter new version (x.y.z format):"
# shellcheck disable=SC2162
read newVersion
echo "--> Dry-run? (y/n):"
# shellcheck disable=SC2162
read isDryRun

if [[ $isDryRun = 'y' ]]
then
  echo "--> Running a dry-run pre-release"
fi

if [[ $newVersion =~ ^([0-9]{1,2}\.){2}[0-9]{1,10}$ ]]; then
  echo "--> Version number: $newVersion is in the correct format"
else
  echo "--> $newVersion is not in the right format."
  exit
fi

if [[ $isDryRun = 'y' ]]
then
  echo "--> Checking out master-dry-run-$newVersion"
  git checkout master-dry-run-"$newVersion"
else
  echo "--> Checking out master"
  git checkout master
fi

git fetch
git pull

pathToMavenLocal=/Users/"$USER"/.m2/repository/com/tink
if [ -e "$pathToMavenLocal" ] ; then
  rm -r "$pathToMavenLocal"
fi

echo "--> clean & assemble"
./gradlew clean
./gradlew assemble
echo "--> publishing to Maven local"
./gradlew publishToMavenLocal

printf "\n\n"
echo "DONE: Pre release completed!"
echo "NEXT: 1) Check if every file has a signed file with the same filename, \
including extension, and an additional .asc file extension"
if [[ $isDryRun = 'y' ]]
then
  echo "NEXT: 2) Launch the post-release.sh script - Skip the release.sh script (Don't publish to Maven central!)"
else
  echo "NEXT: 2) Launch the release.sh script to publish to Maven central"
fi


echo "Press enter to review the files... "
read -r

ls -R /Users/"$USER"/.m2/repository/com/tink/"$newVersion"
open /Users/"$USER"/.m2/repository/com/tink