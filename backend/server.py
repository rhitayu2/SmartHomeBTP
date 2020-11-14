from flask import Flask,redirect, url_for, request
import pymysql
import time
import DBHelper as dbh
import threading
import time
import requests

app = Flask(__name__)

debug_app = 1

localhost = "0.0.0.0"

# All the global variables
global familiar
# global speakers = []

'''
Listing all the utility functions
'''

# Fetching the corresponding sound name of the familiar-patient relationship
def getSoundFromDB(patient_id):
    global familiar
    cursor = dbh.connection.cursor()
    cursor.execute(f"select patient_sound_name from patient where patient_id like '{patient_id}' \
                     and user_name like '{familiar}'")
    soundName = cursor.fetchone()[0]
    return soundName

# Function to query all the Server App Nodes and tell them to find the following BT Address
def sendCorBT(patient_BT_address):
    cursor = dbh.connection.cursor()
    cursor.execute("select speaker_address from speakers")
    speaker_ips = cursor.fetchall()
    ind_ip = []
    for i in range(0,len(speaker_ips)):
        ind_ip.append(speaker_ips[i]['speaker_address'])
    # Function part where we send to each speaker the request
    for sp_ip in ind_ip:
        if debug_app:
            print(sp_ip)
        data = {
            "patient" : patient_BT_address
        }
        try:
            req = requests.get("http://"+sp_ip+"/getStrength/"+patient_BT_address)
        
        except:
            print(f"[-]Host {sp_ip} not reachable")
        
        if debug_app:
            print(f"[!] Trying to send request to {sp_ip} for finding strength")
            print("Response Code => " + sp_ip +" : "+ str(req))


def sendNodeAppSound(soundName, ideal_speaker_ip):
    # A GET method is used to send the sound name
    url = "http://"+ideal_speaker_ip+":8080/playSoundPhone/"+soundName
    try:
        req = requests.get(url)
    except:
        if debug_app:
            print(f"Host {ideal_speaker_ip} unreachable")
    print(req)
    

'''
All routes corresponding to login and then playing sound
'''

# The homepage when accessing the path / 
@app.route('/')
def helloWorld():
    return 'Hello World!!!'

# The API when the user sends the request to login
@app.route('/login', methods=['POST', 'GET'])
def login():
    global familiar
    if request.method == 'POST':
        re = list(request.get_json().items())
        cursor = dbh.connection.cursor()
        username = re[0][1]
        password = re[1][1]
        familiar = username
        if debug_app:
            print(f"username: {username}")
            print(f"password: {password}")
        cursor.execute(f"select user_password from users where user_name like '{username}'")
        pass_from_db = cursor.fetchone()
        if debug_app:
            print(pass_from_db)
        if pass_from_db is None:
            return "-1"
        elif pass_from_db['user_password'] == password:
            cursor.execute(f"select user_admin_flag from users where user_name like '{username}'")
            return str((cursor.fetchone())['user_admin_flag'])
        else:
            return "-1"

# The API to be used when the user needs to fetch all the corresponding patients related to it
@app.route('/fetchSuffererList', methods=['POST', 'GET'])
def fetchSuffere():
    if request.method == POST:
        re = list(request.get_json().items())
        cursor = dbh.connection.cursor()
        username = re[0][1]
        print(username)
        return username

# The API which would be used when we need to find the Patient, we would be provided the 
# patient name, use the function to send all the nodes the request to search the provided 
# patient's BT address
@app.route('/fetchPatientBT', methods=['POST', 'GET'])
def fetchPatientBT():
    if request.method == 'POST':
        re = list(request.get_json().items())
        patient_name = re[0][1]
        cursor = dbh.connection.cursor()
        cursor.execute(f"select patient_BT_address from patient where patient_name like '{patient_name}'")
        patient_BT = cursor.fetchone()
        if debug_app:
            print(f"patient name : {patient_name}")
            print(patient_BT['patient_BT_address'])
        thread_corBT = threading.Thread(target = sendCorBT, args=(patient_BT['patient_BT_address'],))
        thread_corBT.start()
        return "1"

# The API used for all the nodes to send the corresponding BT address, their own IP 
# And simulataneously query the sound of the familiar-patient relationship
@app.route('/sortBTSignal', methods=['POST', 'GET'])
def sortBT():
    global speakers
    if request.method == 'POST':
        re = list(request.get_json().items())
        # Need to sort this out further
        patient_id = request.args.get('patientID')
        patient_distance = request.args.get('patientStrength')
        speaker_address = request.args.get('speakerAddress')
        speakers.append((patient_distance,speaker_address))
        if len(arr) == 4:
            arr.sort()
            ideal_speaker_ip = arr[0][1]
            getSoundName = getSoundFromDB(patient_id)
            sendNodeAppSound(getSoundName,ideal_speaker_ip)
            arr.clear()

'''
All the Admin CRUD functions come here
'''

# @app.route
if __name__ == "__main__":
    # app.run(host = localhost, port = 4444, debug = True)
    app.run(host = localhost, port = 4444)
