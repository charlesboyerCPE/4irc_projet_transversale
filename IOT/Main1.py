# MicroBit 1

# Biliotheque
from microbit import *
import random
import radio
import gc

# Variable Global

jeton = 0

# trame[] =

def receive_data(data):
    time_10ms = 0
    while radio.receive() != data and time_10ms <= 100:
        sleep(10)
        time_10ms = time_10ms + 1

    if time_10ms >= 100:
        return False
    else:
        return True

def wait_ack():
    time_10ms = 0
    while radio.receive() != 'ACK':
        sleep(10)
        time_10ms = time_10ms + 1

        if time_10ms > 100:
            time_10ms = 0
            return False
            break

        time_10ms = 0

    return True

def send_data(data):
    radio.send(data)

# initialisation radio
radio.reset()
radio.config(channel=22)
radio.config(length=251)
radio.on()

# Initialisation UART

uart.init(9600)

# Constante

separators = "( ", ", ", ") ", "] ", "[ "
MAX_COLONNE = 10
MAX_LIGNE = 6
ADDR_MIC_1 = 110
ADDR_MIC_2 = 160


# Initialisation VAR
#msg_split = []
#msg_split = str(msg_split)
data = [[0 for i in range(MAX_COLONNE)] for j in range(MAX_LIGNE)]
data[0][0] = 9
data[5][5] = 9
data[5][3] = 9
data[5][4] = 9
data[5][7] = 9
incoming = None
display.show("I")
sleep(3000)
display.show("1")
uart.write("Pret")
uart.write(str(data))

while True:
    gc.collect()
    incoming = radio.receive()
    if uart.any() :
        msg_bytes = uart.read(181)
        uart.write("\n"+str(msg_bytes)+"\n")
        #msg_split = str(msg_bytes).split(')')
        #msg_split = str(msg_split).split(',')
        #msg_split = str(msg_bytes).split(",")
        #msg_split = str(msg_split).split("]")
        #msg_split = str(msg_split).split("[")
    # radio.send(data)

    if button_a.is_pressed():
        uart.write("\n"+str(msg_bytes)+"\n")
        # msg_split = str(msg_bytes)
        # uart.write("\n"+str(msg_bytes[3])+str(msg_bytes[4])+"\n")
        '''
        for i in range(MAX_LIGNE):
            if i != 0 :
                uart.write(' \n')
            for j in range(MAX_COLONNE):
                uart.write("("+str(i)+","+str(j)+","+str(data[i][j])+")")

        uart.write(";\n")
'''

    if button_b.is_pressed():
        send_data(str(ADDR_MIC_1))
        if receive_data(str(ADDR_MIC_2)+"ACK") == True:
            uart.write("\nreçu ADDR2")
        else:
            uart.write("\nErreur, delai depasse")

        send_data("ACK")
        sleep(10)
        send_data(msg_bytes)

'''
    if button_b.is_pressed():
        send_data(str(ADDR_MIC_1))                                  # Envoie adresse
                                                                    # Attente ACK source et address
        if receive_data(str(ADDR_MIC_2)+"ACK") == True:
            uart.write("\nreçu ADDR2")
        else:
            uart.write("\nErreur, delai depasse")
            break

        send_data("ACK")

        for i in range(MAX_COLONNE):
            for j in range(MAX_LIGNE):
                sleep(20)
                send_data(str(data[j][i]))
                if receive_data("ACK") == True:
                    uart.write("\nreçu l:["+str(j)+"] c:["+str(i)+"]")
                else:
                    uart.write("\nErreur, delai depasse")
'''



