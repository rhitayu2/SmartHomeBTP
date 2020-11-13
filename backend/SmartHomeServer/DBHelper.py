import pymysql

connection = pymysql.connect(host='localhost',
                            user='rhitayu_smarthome',
                            password='smarthome',
                            db='SmartHomeFlask',
                            charset='utf8mb4',
                            cursorclass=pymysql.cursors.DictCursor)
