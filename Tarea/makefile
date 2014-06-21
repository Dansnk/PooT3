JFLAGS = -g
JC = javac
JVM = java
JAR = jar
JDOC = javadoc
APPV = appletviewer

.SUFFIXES: .java .class .html .jar
.java.class:
	$(JC) $(JFLAGS) '.:jmathplot.jar' $*.java
.class.jar:
	$(JAR) PhysicsLabApplet.jar *.class menuhit.wav
	
CLASSES = \
	Ball.java \
	BallView.java \
	FixedHook.java \
	FixedHookView.java \
	Graphview.java \
	LabMenuListener.java \
	MouseListener.java \
    MyWorld.java \
	MyWorldView.java \
	NextKeyListener.java \
	Oscillator.java \
	OscillatorView.java \
    PhysicsElement.java \
	PhysicsLab.java \
	PhysicsLabApplet.java \
	Simulateable.java \
	Spring.java \:
	SpringView.java \
	SpringAttachable.java \

MAIN = PhysicsLabApplet
MAINC = $(MAIN).class
MAINJ = $(MAIN).jar
MAINH = PhysicsLab

default: classes

classes: $(CLASSES:.java=.class)

jar: $(MAINC:.class=.jar)

run:	 
	javac PhysicsLab.java
	java  PhysicsLab
	
runApplet: $(MAIN).class 
		   $(APPV) $(MAINH).html

clean:
	$(RM) *.class
	$(RM) *.jar

doc:
	$(JDOC) $(CLASSES) 