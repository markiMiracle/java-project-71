run-dist:
	./build/install/app/bin/app

build:
	./gradlew clean build

test:
	./gradlew test

lint:
	./gradlew checkstyleMain checkstyleTest

clean: 
	./gradlew clean

report:
	./gradlew jacocTestReport

build-run: build run

.PHONY: build
