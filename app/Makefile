run-dist:
	./build/install/app/bin/app

build:
	./gradlew build

test:
	./gradlew test

lint:
	./gradlew checkstyleMain checkstyleTest

clean: 
	./gradlew clean

report:
	./gradlew jacocTestReport

run:
	./build/install/app/bin/app 
build-run: build run

.PHONY: build
