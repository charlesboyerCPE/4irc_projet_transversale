from microbit import *
import random
import radio

# initialisation radio

radio.reset()
radio.config(channel=22)
radio.config(length=251)
radio.on()

# Initialisation UART

uart.init(9600)

# Constante

MAX_COLONNE = 10
MAX_LIGNE = 6
ADDR_MIC_1 = 110
ADDR_MIC_2 = 160

# Variable timer_10ms > 300

timer_10ms = 0
data = [[0 for i in range(MAX_COLONNE)] for j in range(MAX_LIGNE)]
incoming = None
display.show("2")
sleep(3000)
uart.write("Pret\n")

# fonction# Ecrit ton programme ici ;-)


def send_data(data):
    radio.send(data)
    
def receive_data(data):
    time_10ms = 0
    while radio.receive() != data and time_10ms <= 100 :
        sleep(10)
        time_10ms = time_10ms + 1
        
    if time_10ms >= 300 :
        return False
    else:
        return True

# Boucle infinie

while True:

    if button_a.is_pressed():
        for i in range(MAX_LIGNE):
            if i != 0:
                uart.write(' \n')
            for j in range(MAX_COLONNE):
                uart.write("("+str(j)+","+str(i)+","+str(data[i][j])+")")

        uart.write(";\n")
    
    incoming = radio.receive()
    
    if incoming == str(ADDR_MIC_1):                                     # reception adresse 
        uart.write(str(incoming)+"\n")
        incoming = None
        send_data(str(ADDR_MIC_2)+"ACK")
        incoming = None
        if receive_data("ACK") == True:
            uart.write("\nACK")
            
        timer_10ms = 0
        incoming = radio.receive()
        while incoming is None and timer_10ms <= 1000 :
            sleep(1)
            timer_10ms = timer_10ms + 1
            incoming = radio.receive()
            if incoming is not None:
                uart.write("\n"+incoming)
        

            
        
            
    
'''
    if incoming == str(ADDR_MIC_1):                                     # reception adresse 
        uart.write(str(incoming)+"\n")
        incoming = None
        send_data(str(ADDR_MIC_2)+"ACK")
        incoming = None
        if receive_data("ACK") == True:
            uart.write("\nACK")
            for i in range(MAX_COLONNE):
                for j in range(MAX_LIGNE):
                    send_data("ACK")
                    uart.write("\nACK"+str(i)+str(j))
                    incoming = radio.receive()
                    while incoming == None and timer_10ms <= 300 :
                        incoming = radio.receive()
                        sleep(10)
                        timer_10ms = timer_10ms + 1
                        
                    if timer_10ms >= 300:
                        uart.write("erreur")
                    else: 
                        uart.write(str(incoming))
                    timer_10ms = 0
                    data[j][i] = incoming
            uart.write("fin reception")
        sleep(100)
        incoming = None
    '''