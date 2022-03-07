cd ..
# shellcheck disable=SC2162
read -p "New version number: " version

echo "Publishing to maven central"
./gradlew publish
open https://repo1.maven.org/maven2/com/tink/core
open https://oss.sonatype.org

echo "Polling for release to be complete"
until curl -s -f -o /dev/null "https://repo1.maven.org/maven2/com/tink/core/$version/"
do
  echo "Sleep"
  sleep 5
done

echo "\007"
echo "DONE"