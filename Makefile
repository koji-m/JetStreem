SRCDIR = pw/koj/jetstreem

all: $(SRCDIR)/parser/Parser.class

$(SRCDIR)/parser/Parser.class: $(SRCDIR)/parser/Parser.java
	javac -cp .:dep/jyaml-1.3.jar $(SRCDIR)/parser/Parser.java

$(SRCDIR)/parser/Parser.java: $(SRCDIR)/parser/Parser.jj
	cd $(SRCDIR)/parser/; javacc -debug_parser Parser.jj

clean:
	cd $(SRCDIR)/parser; rm *.class; 
	cd $(SRCDIR)/parser; rm Parser.java
	cd $(SRCDIR)/ast; rm *.class
	cd $(SRCDIR)/compiler; rm *.class
	cd $(SRCDIR)/compiler/ir; rm *.class
	cd $(SRCDIR)/runtime/; rm *.class
	cd $(SRCDIR)/runtime/type; rm *.class

core: 
	javac $(SRCDIR)/core/Main.java

