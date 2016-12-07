#Makehile
.PHONY: all
all: OpenGL
OpenGL: minimum.o
	gcc -o OpenGL minimum.o -lglut -lGLU -lGL -lm

minimum.o: minimum.c
	gcc -c minimum.c -lglut -lGLU -lGL -lm
.PHONY: clean
clean: 
	rm -rf OpenGL minimum.o
