set -e

echo "--> Enter new version (x.y.z format):"
# shellcheck disable=SC2162
read newVersion

if [[ $newVersion =~ ^([0-9]{1,2}\.){2}[0-9]{1,10}$ ]]; then
  echo "--> Version number: $newVersion is in the correct format"
else
  echo "--> $newVersion is not in the right format."
  exit
fi

echo "--> Enter OLD version (x.y.z format):"
# shellcheck disable=SC2162
read oldVersion

if [[ $oldVersion =~ ^([0-9]{1,2}\.){2}[0-9]{1,10}$ ]]; then
  echo "--> Version number: $oldVersion is in the correct format"
else
  echo "--> $oldVersion is not in the right format."
  exit
fi

echo "--> Dry-run? (y/n):"
# shellcheck disable=SC2162
read isDryRun

if [[ $isDryRun = 'y' ]]
then
  privateRepoMasterBranch=master-dry-run-"$newVersion"
  privateRepoPublicMasterBranch=public-master-dry-run
  git checkout public-master
  git pull
  git checkout -b $privateRepoPublicMasterBranch
else
  privateRepoMasterBranch=master
  privateRepoPublicMasterBranch=public-master
fi

git checkout $privateRepoMasterBranch
git fetch
git pull

privateRepoReleaseBranch=release-"$newVersion"
git checkout -b "$privateRepoReleaseBranch"

rm .secrets.baseline
rm -r .buildkite/

git add .secrets.baseline
git add .buildkite/
git commit -m "Release $newVersion"
git rebase -Xtheirs --onto origin/$privateRepoPublicMasterBranch "$oldVersion"-private
git push --set-upstream origin "$privateRepoReleaseBranch"

if [[ $isDryRun = 'y' ]]; then
  git checkout $privateRepoPublicMasterBranch
  git merge --squash "$privateRepoReleaseBranch"
else
  # shellcheck disable=SC2162
  printf "\n\n"
  echo "--> NEXT: Open PR to merge $privateRepoReleaseBranch to [private]/$privateRepoPublicMasterBranch"
  read -p "Press enter when PR is merged"
fi

git checkout $privateRepoMasterBranch
git tag -a v"$newVersion" -m "$newVersion-private"

git checkout $privateRepoPublicMasterBranch
git fetch
git fetch public
git pull

publicRepoReleaseBranch=public-release-"$newVersion"

git checkout -b "$publicRepoReleaseBranch"
git rebase --onto public/master HEAD^1
git push --set-upstream public "$publicRepoReleaseBranch"

printf "\n\n"
echo "--> NEXT: 1) Open PR to merge $publicRepoReleaseBranch into [public]/master with 'Rebase and Merge' option"
echo "--> NEXT: 2) When merged create a new release $newVersion on Github. Don't forget to get an approval for the release notes"
echo "--> NEXT: 3) Grab a beer and celebrate!"
