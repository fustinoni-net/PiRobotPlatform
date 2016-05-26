#
# Makefile:
#	hcsr04 - Java (JNI) library wrapper for WiringPi for the Raspberry Pi
#

TARGET=hcsr04.so

#DEBUG	= -g -O0
DEBUG	= -O3
CC	= gcc
INCLUDE	= -I/usr/lib/jvm/jdk-8-oracle-arm32-vfp-hflt/include -I/usr/lib/jvm/jdk-8-oracle-arm32-vfp-hflt/include/linux -I/usr/local/include -I/usr/local/include/linux
CFLAGS	= $(DEBUG) -Wall $(INCLUDE) -Winline -pipe $(LIBS) 

LIBS    =  -L/usr/local/lib /usr/local/lib/libwiringPi.a /usr/local/lib/libwiringPiDev.a

# Should not alter anything below this line
###############################################################################

SRC	=   net_fustinoni_pi_hcsr04_Hcsr04SinglePin.c

OBJ	=   net_fustinoni_pi_hcsr04_Hcsr04SinglePin.o

all:		$(TARGET)

$(TARGET):	$(OBJ)
	@echo [LINK] $(OBJ)
	@$(CC) $(OBJ) -shared -o $(TARGET) $(INCLUDE) $(LIBS)
	@size   $(TARGET)

.c.o:
	@echo [COMPILE] "$(CC) -c $(CFLAGS)" $<
	@$(CC) -c $(CFLAGS) $< -o $@

clean:
	rm -f $(OBJ) $(TARGET) *~ core tags Makefile.bak

tags:	$(SRC)
	@echo [ctags]
	@ctags $(SRC)

depend:
	makedepend -Y $(SRC)

install:	$(TARGET)
	@echo [install]
	install -m 0755 -d /usr/local/lib
	install -m 0755 -d /usr/local/include
	install -m 0644 $(TARGET)	/usr/local/lib
	# install -m 0644 pi4j.jar	/usr/share/java

uninstall:
	@echo [uninstall]
	rm -f /usr/local/lib/$(TARGET)
	# rm -f /usr/share/java/pi4j.jar


# DO NOT DELETE
net_fustinoni_pi_hcsr04_Hcsr04SinglePin.o: net_fustinoni_pi_hcsr04_Hcsr04SinglePin.h
