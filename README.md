# image-to-ascii
A small program I have written in Kotlin for learning purposes

## Usage
```bash
# build jar using the gradle wrapper
./gradlew jar

# you can name and move it wherever you want
mv build/libs/*.jar ./image-to-ascii.jar

# let's run it
java -jar image-to-ascii.jar cat.jpg ascii-cat.txt
```

Here is a side by side comparison of the cat image I used and the resulting ASCII version

<img src="https://user-images.githubusercontent.com/19901781/64723918-b3b8d900-d4d1-11e9-8cb9-227249a8e541.jpg" width="320" height="180" alt="cat"> <img src="https://user-images.githubusercontent.com/19901781/64723779-75231e80-d4d1-11e9-9d0e-e4c2da099dea.PNG" width="320" height="180" alt="ascii-cat">
