JC=javac

PDIR=com/thenullplayer/d2engine

.PHONY: build clean run


build: clean
	mkdir -p ./out/$(PDIR);
	$(JC) -d ./out --source-path ./src/$(PDIR) @manifest.txt;


run:
	java -classpath out com.thenullplayer.d2engine.Main;


clean:
	rm -rf ./out/com/thenullplayer/d2engine/;
