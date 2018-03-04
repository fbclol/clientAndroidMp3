all: clean build

build:
	slice2java ../interface/server.ice --output-dir app/src/main/java/
clean:
	cd app/src/main/java/ && del app\*.java && cd ../../../..