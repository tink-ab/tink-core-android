cd ..
# shellcheck disable=SC2162
read -p "New version number: " new_version
# shellcheck disable=SC2162
read -p "Previous version number: " old_version
git checkout -b release-"$new_version"

rm . secrets.baseline
rm -r .buildkite/

git add .secrets.baseline
git add .buildkite/
git commit -m "Release new_version"

git rebase --onto origin/public-master "$old_version"-private
git push --set-upstream origin release-"$new_version"

# shellcheck disable=SC2162
read -p "Press enter when merged with [private]/public-master"

git checkout public-master
git fetch
git pull
git checkout -b public-release-"$new_version"
git rebase --onto public/master HEAD^1
git push --set-upstream public release-"$new_version"
