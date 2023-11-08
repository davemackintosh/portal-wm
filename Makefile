.PHONY: build
build:
	./gradlew assemble
	cp ./composeApp/build/outputs/apk/debug/composeApp-debug.apk ~/storage/shared/Download/
	termux-open ~/storage/shared/Download/composeApp-debug.apk
