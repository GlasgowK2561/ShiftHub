#called from Notify.java
#takes email address, start of week date, and formatted email body as command line args
#sends one email with those parameters
#used to send weekly schedule notifications to employees
# -- Written by: Elizabeth

import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email import encoders
import sys
#arg1 - email address
#arg2 - date for subject line
#arg3 - body text

emailFrom = "shiftmateseniorproject@gmail.com"
emailPass = "fatyqvaxplucijvo"
emailTo = str(sys.argv[1])
subject = "ShiftMate Schedule for Week of " + str(sys.argv[2])
body = str(sys.argv[3])

msg = MIMEMultipart()
msg["From"] = emailFrom
msg["To"] = emailTo
msg["Subject"] = subject
msg.attach(MIMEText(body,"plain"))
emailText = msg.as_string()

server = smtplib.SMTP("smtp.gmail.com",587)
server.starttls()
server.login(emailFrom, emailPass)
server.sendmail(emailFrom, emailTo, emailText)
server.quit()
print("EMAIL SENT")