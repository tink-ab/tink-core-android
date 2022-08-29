set -e

echo "--------> Enter NEW version (x.y.z format):"
# shellcheck disable=SC2162
read newVersion
if [[ $newVersion =~ ^([0-9]{1,2}\.){2}[0-9]{1,10}$ ]]; then
   echo ""
else
  echo "--------> $newVersion is not in the right format."
  exit
fi

echo "--------> Enter PREVIOUS version (x.y.z format):"
# shellcheck disable=SC2162
read oldVersion
if [[ $oldVersion =~ ^([0-9]{1,2}\.){2}[0-9]{1,10}$ ]]; then
  echo ""
else
  echo "--------> $oldVersion is not in the right format."
  exit
fi

echo "--------> Enter Jira ticket number (xxxx format - skip PFMF):"
# shellcheck disable=SC2162
read jiraTicketNumber
if [[ $jiraTicketNumber =~ ^[0-9]{4}$ ]]; then
  echo ""
else
  echo "--------> jiraTicketNumber is not in the right format."
  exit
fi

printf "\n\n"
echo "--------> new version number: $newVersion"
echo "--------> previous version number: $oldVersion"
echo "--------> Jira ticket number: $jiraTicketNumber"
read -p "--------> Press enter to start the release process"


git checkout development
git fetch
git pull
echo "--------> Running ktlintFormat"
./gradlew ktlintFormat

if [[ $(git status --porcelain) ]]; then
  echo "--------> There are changes to commit/discard before you can continue with the release"
  exit
else
 echo "--------> No changes to commit/discard, continuing with the release"
fi

branchName=version-bump-"$newVersion"

echo "--------> Creating branch $branchName"
git checkout -b "$branchName"

echo "--------> Updating Version.kt file"
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

echo "--------> Creating version bump commit"
git commit -m "Version bump to $newVersion"
git push --set-upstream origin "$branchName"

gh pr create --repo tink-ab/tink-sdk-core-android --head "$branchName" -t "PFMF-$jiraTicketNumber - Version bump to $newVersion" -b "Version bump to $newVersion" --base development -r tink-ab/android-maintainer
echo "--------> Created version bump PR from $branchName to development"
echo "--------> NEXT: Merge version bump PR"
read -p "--------> Press enter when PR is merged"
read -p "--------> Press enter to confirm PR is merged"

git checkout development
git pull
gh pr create --repo tink-ab/tink-sdk-core-android --head development -t "PFMF-$jiraTicketNumber - Release $newVersion" -b "Release $newVersion" --base master -r tink-ab/android-maintainer
echo "--------> Created release PR from development to master"
echo "--------> NEXT: Merge release PR"
read -p "--------> Press enter when PR is merged"
read -p "--------> Press enter to confirm PR is merged"

echo "--------> Launching the script #2 to publish to Maven local"
./scripts/2_publish_to_maven_local.sh "$newVersion" "$oldVersion" "$jiraTicketNumber"

