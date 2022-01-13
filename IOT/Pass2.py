import serial
import sys
import threading
import socket
import socketserver
import numpy as np
import time
import json
import random as rd
import signal
import argparse
import requests
import json

url = "http://192.168.31.9:80/4irc_projet_transversale/web/ServeurWebEmergency/api/capteurs"


SERIALPORT = "COM7"
BAUDRATE = 9600
ser = serial.Serial()


def initUART():
    # ser = serial.Serial(SERIALPORT, BAUDRATE)
    ser.port = SERIALPORT
    ser.baudrate = BAUDRATE
    ser.bytesize = serial.EIGHTBITS # number of bits per bytes
    ser.parity = serial.PARITY_NONE # set parity check: no parity
    ser.stopbits = serial.STOPBITS_ONE # number of stop bits
    ser.timeout = None # block read
    # ser.timeout = 0 #non-block read
    # ser.timeout = 2 #timeout block read
    ser.xonxoff = False # disable software flow control
    ser.rtscts = False # disable hardware (RTS/CTS) flow control
    ser.dsrdtr = False # disable hardware (DSR/DTR) flow control
    # ser.writeTimeout = 0 #timeout for write


print('Starting Up Serial Monitor')

'''
try:
 ser.open()
except serial.SerialException:
 print("Serial {} port not available".format(SERIALPORT))
 exit()
 
'''


def sendUARTMessage(msg):
    ser.write(msg)
    print("Message <" + msg.decode() + "> sent to micro-controller.")


# Main program logic follows:

if __name__ == '__main__':
    initUART()
    print('Press Ctrl-C to quit.')
    #server = ThreadedUDPServer((HOST, UDP_PORT), ThreadedUDPRequestHandler)
    #server_thread = threading.Thread(target=server.serve_forever)
    #server_thread.daemon = True

    try:
    # server_thread.start()
        ser.open()
        chaine = ''
        while ser.isOpen():
            data_str = ser.read() # met dans data_str un caractère qu'il y a dans l'UART
             # chaine +=str(data_str,'UTF-8')#.decode() #met les caractère dans une chaine et le transforme le type en str à la place de byte
            chaine += data_str.decode()
            if data_str.decode() == ";": #détécte la fin de la trame
                print(chaine)
                data_liste = str.strip(chaine)
                data_liste.remove(";")
                chaine = ""
            '''
            response = requests.get(url)
            print(response)
            
            for i in range (60) :


                response =requests.put(url, data={"id_capteur":i ,"perimetre":7,"coordonnee_x":data_liste[1+3*i],"intensite":data_liste[2+3*i],"coordonnee_y":data_liste[0+3*i]})
                print(response)
            '''  
            
    except:
        print("Serial {} port not available".format(SERIALPORT))