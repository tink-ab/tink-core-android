set -e
cd ..

# TODO: read version from file
echo "New version: "
# shellcheck disable=SC2162
read newVersion
echo "Old version: "
# shellcheck disable=SC2162
read oldVersion

if [[ $newVersion =~ ^([0-9]{1,2}\.){2}[0-9]{1,10}$ ]]; then
echo "Starting post release of version: $newVersion"
else
  echo "$newVersion is not in the right format."
  exit
fi

git checkout master
git fetch
git pull

git checkout -b release-"$newVersion"

rm .secrets.baseline
rm -r .buildkite/

git add .secrets.baseline
git add .buildkite/
git commit -m "Release $newVersion"
git rebase -Xtheirs --onto origin/public-master "$oldVersion"-private
git push --set-upstream origin release-"$newVersion"

# shellcheck disable=SC2162
read -p "Press enter when merged with [private]/public-master"

git checkout public-master
git fetch
git fetch public
git pull

git checkout -b public-release-"$newVersion"
git rebase --onto public/master HEAD^1
git push --set-upstream public public-release-"$newVersion"

echo "$newVersion released!"
