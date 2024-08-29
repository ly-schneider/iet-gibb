import os
import re
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText


def get_settings():
    if os.path.exists("settings.txt"):
        with open("settings.txt", "r") as f:
            content = f.readlines()
        if len(content) == 2:
            return content[0].strip(), content[1].strip()
    return None, None


def send_email(from_email, password, to_email, subject, body):
    msg = MIMEMultipart()
    msg["From"] = from_email
    msg["To"] = to_email
    msg["Subject"] = subject
    msg.attach(MIMEText(body, "plain"))

    try:
        server = smtplib.SMTP("smtp.gmail.com", 587)
        server.starttls()
        server.login(from_email, password)
        text = msg.as_string()
        server.sendmail(from_email, to_email, text)
        server.quit()
        print("E-Mail wurde erfolgreich gesendet!")
    except Exception as e:
        print(f"Fehler beim Senden der E-Mail: {e}")


def main():
    from_email, password = get_settings()

    # Check if the content is correct
    if not from_email or not password:
        print(
            "E-Mail Adresse oder Gmail App-Code ist leer. Bitte konfigurieren Sie die E-Mail Adresse und das Passwort in settings.txt"
        )
        return

    print()
    to_email = input(
        "Gebe die E-Mail Adresse ein, an die die E-Mail gesendet werden soll: "
    )

    # If email does not match the regex pattern
    if not re.match(r"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$", to_email):
        print("Die E-Mail Adresse ist ungültig.")
        return

    subject = input("Gebe den Betreff der E-Mail ein: ")
    if not subject:
        print("Der Betreff der E-Mail darf nicht leer sein.")
        return

    body = ""
    while True:
        line = input(
            "Gebe den Inhalt der E-Mail ein (oder 'SEND' um zu senden der E-Mail): "
        )
        if line == "SEND":
            break
        body += line + "\n"

    if not body.strip():
        print("Der Inhalt der E-Mail darf nicht leer sein.")
        return

    send_email(from_email, password, to_email, subject, body)


if __name__ == "__main__":
    main()
