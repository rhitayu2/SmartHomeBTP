import DBHelper as dbh

# All admin CRUD functions

cursor = dbh.connection.cursor()

#User Related functions

def addUser(username, password, ifAdmin):    
    cursor = dbh.connection.cursor()
    try:
        cursor.execute(f"insert into users users(user_name, user_password,user_admin_flag) \
        values ('{username}','{password}',{ifAdmin})")
        dbh.connection.commit()
        return "1"
    except:
        return "-1"

def deleteUser(username):
    cursor.execute("delete from users where user_name like %s", (username))
    dbh.connection.commit()

def updateUserPassword(username, new_password):
    cursor.execute("update users set user_password = %s where user_name like %s", (new_password, username))
    dbh.connection.commit()

def displayUsers():
    cursor.execute("select user_name from users")
    users = cursor.fetchall()
    return users

def displaySpeakers():
    cursor.execute("select speaker_address from speakers")
    speakers = cursor.fetchall()
    return speakers

#Patient Related Functions

def displayPatients():
    cursor.execute("select patient_name from patient")
    patients = cursor.fetchall()
    return patients

def addPatient(patientName, patient_BT_address, patient_sound_name):    
    cursor.execute("insert into users (patient_name, patient_BT_address, patient_sound_name) values (%s,%s,%s)", (patientName, patient_BT_address, patient_sound_name))
    dbh.connection.commit()

# addUser("akusfh", "asufha", 1)  
displayUsers()