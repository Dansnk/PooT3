JFLAGS = -g
JC = javac
JVM = java

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) -cp '.:jmathplot.jar' $*.java
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
	Spring.java \
	SpringView.java \
	SpringAttachable.java \

default: classes

classes: $(CLASSES:.java=.class)

jar: $(MAINC:.class=.jar)

run:	
	java  -cp '.:jmathplot.jar' PhysicsLab
	
runApplet: 
	appletviewer PhysicsLabApplet.html

clean:
	$(RM) *.class PhysicsLab.jar
	$(RM) *.jar
