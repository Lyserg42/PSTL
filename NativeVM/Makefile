

CC = gcc
CFLAGS = -g -pthread

SOURCES = value.h value.c varray.h varray.c env.h env.c frame.h frame.c vm.h vm.c prim.c gc.h gc.c gc_mark.c bytecode.h bytecode.c main.c
OBJECTS = constants.o value.o varray.o env.o frame.o prim.o vm.o gc_mark.o gc.o bytecode.o main.o

CTOPDIR = ../JCompiler
CTOP = java -jar $(CTOPDIR)/jcompiler.jar

all: constants main


constants: constants.h constants.c
	echo "Constants generated"

constants.h: prepare

constants.c: prepare

prepare:
	$(CTOP) -vmconst

main : $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o runvm

%.o : %.c
	$(CC) $(CFLAGS) -c $<

docs:
	doxygen

archive: Makefile $(SOURCES)
	tar cvzf nativevm.tar.gz Makefile $(SOURCES) 

clean:
	rm -f $(OBJECTS)
	rm -f *~

cleanall: clean
	rm -f constants.h
	rm -f constants.c
	rm -f runvm
	rm -rf apidoc
