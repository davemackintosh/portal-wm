UBUNTU:=proot-distro login ubuntu --shared-tmp --termux-home
APP_NAME:=composeApp

.PHONY: termux-deps
termux-deps:
	${UBUNTU} -- apt install -y android-platform-tools-base android-sdk openjdk-17-jdk sdkmanager gradle
	${UBUNTU} -- sdkmanager "platform-tools" "platforms;android-33" "build-tools;30.0.2"

.PHONY: build
build:
	${UBUNTU} -- /root/www/kotlin/desk/build-in-proot.sh
	cp ./${APP_NAME}/build/outputs/apk/debug/${APP_NAME}-debug.apk ~/storage/shared/Download/
	# termux-open ~/storage/shared/Download/${APP_NAME}-debug.apk
