from flask import Flask,redirect, url_for, request
import pymysql
import time
import DBHelper as dbh
import threading
import time
import requests
import adminCRUDFunctions as admin


app = Flask(__name__)
app.config.from_object('config')


debug_app = app.config["DEBUG_APP_ROUTES"]
localhost = app.config["LOCAL_HOST"]

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
            print(f"Speaker IP : {sp_ip}")
        data = {
            "patient" : patient_BT_address
        }
        try:
            req = requests.get("http://"+sp_ip+"/getStrength/"+patient_BT_address)
            print(f"[+] Host {sp_ip} is reachable\n")
        
        except:
            print(f"[-]Host {sp_ip} not reachable\n")
        
        # if debug_app:
            # print("Response Code => " + sp_ip +" : "+ str(req))

def sendNodeAppSound(soundName, ideal_speaker_ip):
    # A GET method is used to send the sound name
    url = "http://"+ideal_speaker_ip+":8080/playSoundPhone/"+soundName
    try:
        req = requests.get(url)
        if debug_app:
            print(f"[+] Sending {soundName} to {speaker_ip}")
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
            print(f"password from DB : {pass_from_db['user_password']}\n")
        if pass_from_db is None:
            return "-1"
        elif pass_from_db['user_password'] == password:
            cursor.execute(f"select user_admin_flag from users where user_name like '{username}'")
            return str((cursor.fetchone())['user_admin_flag'])
        else:
            return "-1"

# The API to be used when the user needs to fetch all the corresponding patients related to it
@app.route('/fetchSuffererList', methods=['POST', 'GET'])
def fetchSufferer():
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
            print(f"patient_BT_address : {patient_BT['patient_BT_address']}")
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
        patient_id = re[0][1]
        patient_distance = re[1][1]
        speaker_ip = re[2][1]
        if debug_app:
            print(f"[+] RSSI value from {speaker_ip} : {patient_distance} dB")
        speakers.append((patient_distance,speaker_address))
        if len(arr) == 4:
            arr.sort()
            ideal_speaker_ip = arr[0][1]
            getSoundName = getSoundFromDB(patient_id)
            if debug_app:
                print(f"[!] Related sound name : {getSoundName}")
            sendNodeAppSound(getSoundName,ideal_speaker_ip)
            arr.clear()

'''
All the Admin CRUD functions come here
'''

@app.route('/addFamiliar', methods = ['POST', 'GET'])
def addFamiliar():
    if request.method == 'POST':
        re = list(request.get_json().items())
        username = re[0][1]
        password = re[1][1]
        adminFlag = int(re[2][1])
        if debug_app:
            print(username)
            print(password)
            print(adminFlag)
        try:
            cursor = dbh.connection.cursor()
            cursor.execute(f"insert into users(user_name, user_password, user_admin_flag) \
                values('{username}', '{password}',{adminFlag})")
            dbh.connection.commit()
            return "1"
        except:
            return "-1"

@app.route('/deleteFamiliar', methods=['POST', 'GET'])
def deleteFamiliar():
    if request.method == 'POST':
        re = list(request.get_json().items())
        username = re[0][1]
        password = re[1][1]
        if debug_app:
            print(username)
            print(password)
        try:
            cursor = dbh.connection.cursor()
            cursor.execute(f"delete from users where user_name like '{username}' and \
                            user_password like '{password}'")
            dbh.connection.commit()
            return "1"
        except:
            return "-1"

@app.route('/addSufferer', methods=['POST', 'GET'])
def addSufferer():
    if request.method == 'POST':
        re = list(request.get_json().items())
        patientName = re[0][1]
        patientBT = re[1][1]
        patientSound = re[2][1]
        if debug_app:
            print(patientName)
            print(patientBT)
            print(patientSound)
        try:
            cursor = dbh.connection.cursor()
            cursor.execute(f"insert into patient (patient_name, patient_BT_address, patient_sound_name) \
                values ('{patientName}', '{patientBT}', '{patientSound}')")
            dbh.connection.commit()
            return "1"
        except:
            return "-1"


@app.route('/deleteSufferer', methods=['POST', 'GET'])
def deleteSufferer():
    if request.method == 'POST':
        re = list(request.get_json().items())
        patientName = re[0][1]
        if debug_app:
            print(patientName)
        try:
            cursor = dbh.connection.cursor()
            cursor.execute(f"delete from patient where patient_name like '{patientName}'")
            dbh.connection.commit()
            return "1"
        except:
            return "-1"

@app.route('/addFS', methods=['POST', 'GET'])
def addFS():
    if request.method == 'POST':
        re = list(request.get_json().items())
        familiarName = re[0][1]
        patientName = re[1][1]
        soundName = re[2][1]
        cursor = dbh.connection.cursor()
        cursor.execute(f"select user_id from users where user_name like '{familiarName}'")
        familiarID = int(cursor.fetchone()['user_id'])
        cursor.execute(f"select patient_id from patient where patient_name like '{patientName}'")
        patientID = int(cursor.fetchone()['patient_id'])
        if debug_app:
            print(familiarName)
            print(patientName)
            print(soundName)
            print(familiarID)
            print(patientID)
        try:
            cursor = dbh.connection.cursor()
            cursor.execute(f"insert into familiar_patient values( {familiarID}, '{familiarName}', {patientID},\
                '{patientName}', '{soundName}')")
            dbh.connection.commit()
            return "1"
        except:
            return "-1"

@app.route('/deleteFS', methods=['POST', 'GET'])
def deleteFS():
    if request.method == 'POST':
        re = list(request.get_json().items())
        familiarName = re[0][1]
        patientName = re[1][1]
        if debug_app:
            print(familiarName)
            print(patientName)
        try:
            cursor = dbh.connection.cursor()
            cursor.execute(f"delete from familiar_patient where patient_name like '{patientName}' and \
                            user_name like '{familiarName}'")
            dbh.connection.commit()
            return "1"
        except:
            return "-1"

@app.route('/addSpeaker', methods = ['POST', 'GET'])
def addSpeaker():
    if request.method == 'POST':
        re = list(request.get_json().items())
        speakerIP = re[0][1]
        speakerIP += ":8080"
        if debug_app:
            print(speakerIP)
        try:
            cursor = dbh.connection.cursor()
            cursor.execute(f"insert into speakers (speaker_address) values ('{speakerIP}')")
            dbh.connection.commit()
            return "1"
        except:
            return "-1"

@app.route('/deleteSpeaker', methods = ['POST', 'GET'])
def deleteSpeaker():
    if request.method == 'POST':
        re = list(request.get_json().items())
        speakerIP = re[0][1]
        speakerIP += ":8080"
        if debug_app:
            print(speakerIP)
        try:
            cursor = dbh.connection.cursor()
            cursor.execute(f"delete from speakers where speaker_address like '{speakerIP}'")
            dbh.connection.commit()
            return "1"
        except:
            return "-1"


# @app.route
if __name__ == "__main__":
    # app.run(host = localhost, port = 4444, debug = True)
    app.run(host = localhost, port = 4444)
