#Makefile
#Aaron Hom
#awhom
#Lab 3
#A makefile for charType.c


FLAGS = -std=c99 -Wall
SOURCES = charType.c
OBJECTS = charType.o
EXEBIN = charType

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm $(EXEBIN) $(OBJECTS)
check : 
	valgrind --leak-check=full $(EXEBIN) $(SOURCES) out
