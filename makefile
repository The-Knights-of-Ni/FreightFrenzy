.PHONY: all build_javadoc build adb_connect

all: build adb_connect

build_javadoc:
	@echo "Building Javadoc"
	javadoc org.firstinspires.ftc.teamcode -sourcepath Teamcode/src/main/java -J-Xmx4096m -private -splitindex  -html5 -d javadoc

build:
	@echo "Building Project"
	bash gradlew

adb_connect:
	@echo "Connecting via adb"
	/Library/Android/platform-tools/adb connect 192.168.43.1:5555
