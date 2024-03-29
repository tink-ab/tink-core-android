# shellcheck disable=SC2162

newVersion=$1
oldVersion=$2
jiraTicketNumber=$3
isDryRun=$4

git fetch

echo "--------> Initializing branches"
if [[ $isDryRun = 'y' ]]
then
  privateRepoMasterBranch=master-dry-run
  publicRepoMasterBranch=master-dry-run-public
  privateRepoPublicMasterBranch=public-master-dry-run
  privateRepoReleaseBranch2=clean-release-"$newVersion"-dry-run
  publicRepoReleaseBranch=public-release-"$newVersion"-dry-run
  git push origin --delete $publicRepoMasterBranch
  git push origin --delete $privateRepoPublicMasterBranch
else
  privateRepoMasterBranch=master
  publicRepoMasterBranch=master-public
  privateRepoPublicMasterBranch=public-master
  privateRepoReleaseBranch2=clean-release-"$newVersion"
  publicRepoReleaseBranch=public-release-"$newVersion"
fi

echo "--------> Cleanup branches"

git branch -d $publicRepoMasterBranch
git switch -c $publicRepoMasterBranch public/master
git push --set-upstream public $publicRepoMasterBranch

git branch -d $privateRepoPublicMasterBranch
git switch -c $privateRepoPublicMasterBranch origin/public-master
git push --set-upstream origin $privateRepoPublicMasterBranch

git branch -d "$privateRepoReleaseBranch2"
git push origin --delete "$privateRepoReleaseBranch2"
git switch -c "$privateRepoReleaseBranch2" $privateRepoMasterBranch
git push --set-upstream origin "$privateRepoReleaseBranch2"

git branch -d "$publicRepoReleaseBranch"
git push public --delete "$publicRepoReleaseBranch"

echo "--------> Cleanup tags"
git tag -d "$newVersion-private"
git push origin --delete "$newVersion-private"

git tag -d "$newVersion"
git push public --delete "$newVersion"

echo "--------> Checkout $privateRepoMasterBranch"
git checkout $privateRepoMasterBranch
git fetch
git pull

git checkout "$privateRepoReleaseBranch2"

rm .secrets.baseline
rm -r .buildkite/
git add .secrets.baseline
git add .buildkite/
git commit -m "Clean release $newVersion from secrets file"
git rebase -Xtheirs --onto origin/$privateRepoPublicMasterBranch "$oldVersion"-private
git push -f
echo "--------> Removed buildkite and .secrets.baseline file from release branch $privateRepoReleaseBranch2"

if [[ $isDryRun = 'y' ]]
then
  prTitlePrefix="DRY-RUN"
else
  prTitlePrefix=""
fi
gh pr create --repo tink-ab/tink-sdk-core-android --head "$privateRepoReleaseBranch2" -t "$prTitlePrefix Release $newVersion" -b "Release $newVersion" --base $privateRepoPublicMasterBranch -r tink-ab/android-maintainer
clear
echo "--------> DONE: Opened PR to merge $privateRepoReleaseBranch2 to $privateRepoPublicMasterBranch"
echo "--------> NEXT: IMPORTANT! Merge release PR using SQUASH OPTION!"
read -p "--------> Press enter when PR is merged"
read -p "--------> Press enter to confirm PR is merged"

git checkout $privateRepoPublicMasterBranch
git fetch
git fetch public
git pull

read -p "--------> Debug"

git checkout -b "$publicRepoReleaseBranch"
git push --set-upstream public "$publicRepoReleaseBranch"
git rebase --onto public/$publicRepoMasterBranch HEAD^1
git push -f
echo "--------> Created a release branch $publicRepoReleaseBranch on the public repo"

gh pr create --repo tink-ab/tink-core-android --head "$publicRepoReleaseBranch" -t "$prTitlePrefix Release $newVersion" -b "Release $newVersion" --base $publicRepoMasterBranch -r tink-ab/android-maintainer
clear
echo "--------> DONE: Opened PR to merge $publicRepoReleaseBranch to [public]/$publicRepoMasterBranch"
echo "--------> NEXT: IMPORTANT! Merge release PR using 'REBASE AND MERGE' OPTION! !!"
read -p "--------> Press enter when PR is merged"
read -p "--------> Press enter to confirm PR is merged"


git checkout $privateRepoMasterBranch
git pull
git tag -a "$newVersion-private" -m "$newVersion-private"
git push origin --tags
echo "--------> Tagged origin/$privateRepoMasterBranch branch with tag: $newVersion-private"

git checkout -b master-public-repo --track public/$publicRepoMasterBranch
git pull
git tag -a v"$newVersion" -m "$newVersion"
git push public --tags
echo "--------> Tagged public/$publicRepoMasterBranch branch with tag: $newVersion"

gh release create v"$newVersion" -d --repo tink-ab/tink-core-android --target $publicRepoMasterBranch -t "Release $newVersion" -n "Add release notes here"
echo "--------> Created a draft release $newVersion on public repo"
echo "--------> NEXT: FINAL STEP: Add the release notes and publish the release"

