set -e

newVersion=$1
oldVersion=$2

git fetch

# ----------- DRY RUN INIT - START
# Create copy of public/master, origin/master and origin/public-master

git switch -c master-dry-run-public-repo public/master
git push --set-upstream public master-dry-run-public-repo

git checkout master
git pull
git checkout -b master-dry-run-private-repo
touch dry-run.test
git add .
git commit -m "dry run commit"
git push --set-upstream origin master-dry-run-private-repo

git checkout public-master
git pull
git checkout -b public-master-dry-run-private-repo
git push --set-upstream origin public-master-dry-run-private-repo

# -------------- DRY RUN INIT - END

privateRepoMasterBranch=master-dry-run-private-repo
privateRepoPublicMasterBranch=public-master-dry-run-private-repo
publicRepoMasterBranch=master-dry-run-public-repo
echo "--------> Initialized branches"

git checkout $privateRepoMasterBranch
git fetch
git pull

privateRepoReleaseBranch=release-"$newVersion"
git checkout -b "$privateRepoReleaseBranch"
echo "--------> Created release branch $privateRepoReleaseBranch in private repo"

rm .secrets.baseline
rm -r .buildkite/
git add .secrets.baseline
git add .buildkite/
git commit -m "Release $newVersion"
git rebase -Xtheirs --onto origin/$privateRepoPublicMasterBranch "$oldVersion"-private
git push --set-upstream origin "$privateRepoReleaseBranch"
echo "--------> Removed buildkite and .secrets.baseline file from release branch $privateRepoReleaseBranch"

gh pr create --repo tink-ab/tink-sdk-core-android --head "$privateRepoReleaseBranch" -t "Release $newVersion" -b "Release $newVersion" --base $privateRepoPublicMasterBranch -r tink-ab/android-maintainer
echo "--------> Opened PR to merge $privateRepoReleaseBranch to $privateRepoPublicMasterBranch"
echo "--------> !! Remember to merge with the 'Squash' option !!"
read -p "--------> Press enter when PR is merged"
read -p "--------> Press enter to confirm PR is merged"

git checkout $privateRepoMasterBranch
git pull
git tag -a v"$newVersion-private" -m "$newVersion-private"
git push origin --tags
echo "--------> Tagged origin/$privateRepoMasterBranch branch with tag: $newVersion-private"

git checkout $privateRepoPublicMasterBranch
git fetch
git fetch public
git pull

publicRepoReleaseBranch=public-release-"$newVersion"

git checkout -b "$publicRepoReleaseBranch"
git rebase --onto public/$publicRepoMasterBranch HEAD^1
git push --set-upstream public "$publicRepoReleaseBranch"
echo "--------> Created a release branch $publicRepoReleaseBranch on the public repo"

gh pr create --repo tink-ab/tink-core-android --head "$publicRepoReleaseBranch" -t "Release $newVersion" -b "Release $newVersion" --base $publicRepoMasterBranch -r tink-ab/android-maintainer
echo "--------> Opened PR to merge $privateRepoReleaseBranch to [public]/$publicRepoMasterBranch"
echo "--------> !! Remember to merge with the 'Rebase and Merge' option !!"
read -p "--------> Press enter when PR is merged"
read -p "--------> Press enter to confirm PR is merged"

git checkout -b master-public-repo --track public/$publicRepoMasterBranch
git pull
git tag -a v"$newVersion" -m "$newVersion"
git push public --tags
echo "--------> Tagged public/$publicRepoMasterBranch branch with tag: $newVersion"

gh release create v"$newVersion" -d --repo tink-ab/tink-core-android --target $publicRepoMasterBranch -t "Release $newVersion" -n "Add release notes here"
echo "--------> Created a draft release $newVersion on public repo"
echo "--------> NEXT: FINAL STEP: Add the release notes and publish the release"

