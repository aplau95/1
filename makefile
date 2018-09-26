JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		Student.java \
		SchoolSearch.java 		

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class
