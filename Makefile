JC=javac

PDIR=com/thenullplayer/d2engine

.PHONY: build clean run


build: clean
	mkdir -p ./out/$(PDIR);
	$(JC) -d ./out --source-path ./src/$(PDIR) @manifest.txt;


run: build
	java -classpath out com.thenullplayer.d2engine.Main;

sprite-editor: build
	java -classpath out com.thenullplayer.d2engine.EditorSprite;

clean:
	rm -rf ./out/com/thenullplayer/d2engine/;
