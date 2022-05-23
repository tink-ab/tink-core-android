set -e

echo "--> Running ktlintFormat"
./gradlew ktlintFormat

if [[ $(git status --porcelain) ]]; then
  echo "--> There are changes to commit/discard before you can continue with the release"
  exit
else
 echo "--> No changes to commit/discard, continuing with the release"
fi

echo "--> Enter new version (x.y.z format):"
# shellcheck disable=SC2162
read newVersion
echo "--> Dry-run? (y/n):"
# shellcheck disable=SC2162
read isDryRun

if [[ $newVersion =~ ^([0-9]{1,2}\.){2}[0-9]{1,10}$ ]]; then
echo "--> Version number: $newVersion is in the correct format"
else
  echo "--> $newVersion is not in the right format."
  exit
fi

branchName=version-bump-"$newVersion"
if [[ $isDryRun = 'y' ]]
then
  masterBranch=master-dry-run-"$newVersion"
else
  masterBranch=master
fi

echo "--> Creating branch $branchName"
git checkout development
git fetch
git pull
git checkout -b "$branchName"

echo "--> Updating Version.kt file"
major=$(echo "$newVersion" | cut -d. -f1)
minor=$(echo "$newVersion" | cut -d. -f2)
patch=$(echo "$newVersion" | cut -d. -f3)

versionFilePath="./buildSrc/src/main/java/Version.kt"

rm $versionFilePath
echo "object Version {

    private const val major = $major
    private const val minor = $minor
    private const val patch = $patch

    const val name = \"\$major.\$minor.\$patch\"

    private const val minorOffset = 100 //make space for 100 patches per minor version
    private const val majorOffset = 100 * minorOffset //make space for 100 minor versions per major version

    //Will generate a readable int representation of the version
    //For example 4.12.3 will be 412003
    const val code = major * majorOffset + minor * minorOffset + patch
}
" >> $versionFilePath
git add $versionFilePath

echo "--> Creating version bump commit"
git commit -m "Version bump to $newVersion"
git push --set-upstream origin "$branchName"

printf "\n\n"
echo "--> DONE: Version bumped to $newVersion!"
if [[ $isDryRun = 'y' ]]
then
  git checkout master
  git pull
  git checkout -b $masterBranch
  git merge "$branchName" -m "Dry-run merge $branchName into $masterBranch"
  git push --set-upstream origin $masterBranch
  printf "\n\n"
  echo "--> NEXT: Launch (in dry-run mode!) the script to publish to Maven local"
else
  echo "--> NEXT: 1) Open Prs to merge $branchName to development and then development to master"
  echo "--> NEXT: 2) Launch the script to publish to Maven local"
fi
printf "\n\n"
