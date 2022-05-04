set -e
cd ..

pathToMavenLocal=/Users/"$USER"/.m2/repository/com/tink
if [ -e "$pathToMavenLocal" ] ; then
  rm -r "$pathToMavenLocal"
fi

git checkout master
git fetch
git pull

./gradlew clean
./gradlew assemble
./gradlew publishToMavenLocal

echo Maven Local Files
echo Check if every file has a signed file with the same filename, \
including extension, and an additional .asc file extension
ls -R /Users/"$USER"/.m2/repository/com/tink
open /Users/"$USER"/.m2/repository/com/tink