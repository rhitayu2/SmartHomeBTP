from flask import Flask,redirect, url_for, request
import pymysql
import time
import DBHelper as dbh
import threading
import time

app = Flask(__name__)

debug_app = 1

#Connecting to MySQL server

localhost = "0.0.0.0"
# global speakers = []

# Utility Functions
def getSoundFromDB(patient_id):
    cursor = dbh.connection.cursor()
    cursor.execute("select patient_sound_name from patient where patient_id = %s",(patient_id))
    soundName = cursor.fetchone()[0]
    return soundName

def sendCorBT(patient_name):
    cursor = dbh.connection.cursor()
    cursor.execute("select speaker_address from speakers")
    speaker_ips = cursor.fetchall()
    ind_ip = []
    for i in range(0,len(speaker_ips)):
        ind_ip.append(speaker_ips[i]['speaker_address'])
    print(ind_ip)

# All functions corresponding to login and then playing sound
@app.route('/')
def helloWorld():
    return 'Hello World!!!'

@app.route('/login', methods=['POST', 'GET'])
def login():
    if request.method == 'POST':
        re = list(request.get_json().items())
        cursor = dbh.connection.cursor()
        username = re[0][1]
        password = re[1][1]
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
        

@app.route('/fetchSuffererList', methods=['POST', 'GET'])
def fetchSuffere():
    if request.method == POST:
        re = list(request.get_json().items())
        cursor = dbh.connection.cursor()
        username = re[0][1]
        print(username)
        return username

@app.route('/sortBTSignal', methods=['POST', 'GET'])
def sortBT():
    global speakers
    if request.method == 'POST':
        patient_id = request.args.get('patientID')
        patient_distance = request.args.get('patientStrength')
        speaker_address = request.args.get('speakerAddress')
        speakers.append((patient_distance,speaker_address))
        if len(arr) == 4:
            arr.sort()
            ideal_speaker_ip = arr[0][1]
            getSoundName = getSoundFromDB(patient_id)
            # Server --> [speaker_ip, sound_name]
            # Code where the ideal speaker gets the request to deal with
            arr.clear()





#CRUD Functions

# @app.route
if __name__ == "__main__":
    # app.run(host = localhost, port = 4444, debug = True)
    app.run(host = localhost, port = 4444)
