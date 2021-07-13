# Default target- Runs the program
default: compile 
	java FridgeOrganizer
	
compile: FridgeOrganizer.class HashTableMap.class LinkedHashNode.class FoodItemNode.class FoodDescription.class MapADT.class

FridgeOrganizer.class: FridgeOrganizer.java
	javac FridgeOrganizer.java 
HashTableMap.class: HashTableMap.java
	javac HashTableMap.java
LinkedHashNode.class: LinkedHashNode.java
	javac LinkedHashNode.java
FoodItemNode.class: FoodItemNode.java
	javac FoodItemNode.java
FoodDescription.class: FoodDescription.java
	javac FoodDescription.java
MapADT.class: MapADT.java
	javac MapADT.java

# Test target- runs the test class
test: FridgeTest.class
	java -jar junit5.jar -cp . --scan-classpath -n FridgeTest

FridgeTest.class: FridgeTest.java
	javac FridgeTest.java
	javac -cp .:junit5.jar FridgeTest.java -Xlint

# Clean target- Removes all class files from the directory
clean:
	rm *.class
