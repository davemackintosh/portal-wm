UBUNTU:=proot-distro login ubuntu --shared-tmp --termux-home
APP_NAME:=composeApp

.PHONY: termux-deps
termux-deps:
	wget https://github.com/Lzhiyong/termux-ndk/releases/download/android-sdk/android-sdk-aarch64.zip
	unzip android-sdk-aarch64.zip -d $$PREFIX/share
	@echo "Android SDK installed to $$PREFIX/share/android-sdk"
	direnv allow
	sdkmanager "platform-tools" "platforms;android-33"
	rm -f android-sdk-aarch64.zip*

.PHONY: termux-ubuntu-deps
termux-ubuntu-deps:
	${UBUNTU} -- apt install -y android-platform-tools-base android-sdk openjdk-17-jdk sdkmanager gradle
	${UBUNTU} -- sdkmanager "platform-tools" "platforms;android-33" "build-tools;30.0.2"

.PHONY: build
build:
	#${UBUNTU} -- /root/www/kotlin/desk/build-in-proot.sh
	./gradlew assemble
	cp ./${APP_NAME}/build/outputs/apk/debug/${APP_NAME}-debug.apk ~/storage/shared/Download/
	# termux-open ~/storage/shared/Download/${APP_NAME}-debug.apk
