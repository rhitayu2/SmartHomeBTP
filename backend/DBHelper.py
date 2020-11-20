import pymysql
import sys

try:
	connection = pymysql.connect(host='localhost',
                            user='rhitayu_smarthome',
                            password='smarthome',
                            db='SmartHomeFlask',
                            charset='utf8mb4',
                            cursorclass=pymysql.cursors.DictCursor)
except:
	print("[-] MySQL Server is not reachable...")
	print("[-] Exiting...")
	sys.exit(0)