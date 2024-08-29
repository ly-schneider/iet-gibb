import os
import time
import requests
from xml.etree import ElementTree as ET
from datetime import datetime

# Create LOG Directory if it does not exist
log_dir = "./LOGS"
if not os.path.exists("./LOGS"):
    os.makedirs("./LOGS")


def get_settings():
    if os.path.exists("settings.txt"):
        with open("settings.txt", "r") as f:
            content = f.readlines()
        if len(content) == 2:
            return content[0].strip(), content[1].strip()
    return None, None


def fetch_emails():
    with open("./settings.txt", "r") as file:
        content = file.readlines()
        email = content[0].strip()
        password = content[1].strip()

    # Access the rss-feed
    response = requests.get(
        "https://mail.google.com/mail/feed/atom", auth=(email, password)
    )

    # Parse the xml response
    xml = ET.fromstring(response.content)

    entries = xml.findall("{http://purl.org/atom/ns#}entry")

    if not entries:
        print("\nKeine ungelesenen E-Mails vorhanden\n")
    else:
        for entry in entries:
            issued = entry.find("{http://purl.org/atom/ns#}issued").text
            author_email = (
                entry.find("{http://purl.org/atom/ns#}author")
                .find("{http://purl.org/atom/ns#}email")
                .text
            )
            title = entry.find("{http://purl.org/atom/ns#}title").text
            summary = entry.find("{http://purl.org/atom/ns#}summary").text

            print(f"\nZugestellt: {issued}")
            print(f"E-Mail: {author_email}")
            print(f"Subjekt: {title}")
            print(f"Nachricht: {summary}")

    # Create a log file with the fetched emails
    timestamp = datetime.now().strftime("%Y-%m-%d_%H-%M-%S")

    with open(f"./LOGS/EmailLog_{timestamp}.txt", "a") as log_file:
        for entry in entries:
            issued = entry.find("{http://purl.org/atom/ns#}issued").text
            author_email = (
                entry.find("{http://purl.org/atom/ns#}author")
                .find("{http://purl.org/atom/ns#}email")
                .text
            )
            title = entry.find("{http://purl.org/atom/ns#}title").text
            summary = entry.find("{http://purl.org/atom/ns#}summary").text
            log = f"Zugestellt: {issued}\nE-Mail: {author_email}\nSubjekt: {title}\nNachricht: {summary}\n\n"
            log_file.write(log)

    print("\n----------------------------------------")


def main():
    while True:
        fetch_emails()
        time.sleep(15)


if __name__ == "__main__":
    main()
