#Makefile
.PHONY: all
all: jaki

jaki: jaki.o
	gcc -o jaki jaki.o

jaki.o: jaki.c
	gcc -c jaki.c

jaki.o: jaki.h

.PHONY: clean
clean:
	rm -rf jaki jaki.o
