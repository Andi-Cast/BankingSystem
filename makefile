Main.class: Main.java
	javac -g Main.java
clean: 
	rm *.class
run: Main.class
	java Main
debug: 
	jdb Main
