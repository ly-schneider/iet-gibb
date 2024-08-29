import os
import re
import sqlite3
import bcrypt

# Connect to database and create account table if it doesn't exist
con = sqlite3.connect("accounts.db")
cur = con.cursor()
cur.execute(
    """
    CREATE TABLE IF NOT EXISTS account(
        email TEXT NOT NULL,
        password TEXT NOT NULL,
        app_code TEXT NOT NULL
    )
"""
)

con.commit()


def choose_login_register(text):
    if text:
        print(f"|  {text}")
        print("|")

    print("|  Loggen Sie sich ein oder registrieren Sie sich:")
    print("|")

    # Menu Selector
    print("|  Bitte wählen Sie eine Option:")
    print("|  1 (Login)")
    print("|  2 (Registrieren)")
    print("|  3 (Exit)")
    print("|  ")

    option = input("|  Option wählen: ")

    if option == "1":
        login()
    elif option == "2":
        create_account()
    elif option == "3":
        print()
        print("|  Auf Wiedersehen!")
    else:
        print("|  Ungültige Option")


def login():
    email = input("|  Bitte geben Sie Ihre E-Mail Adresse ein: ")

    cur.execute("SELECT password, app_code FROM account WHERE email = ?", (email,))
    row = cur.fetchone()

    if row:
        password = None

        while password is None:
            password = input("|  Bitte geben Sie Ihr Passwort ein: ")

            stored_hashed_password = row[0]

            # Verify the input password against the stored hashed password
            if bcrypt.checkpw(
                password.encode("utf-8"), stored_hashed_password.encode("utf-8")
            ):
                print("|")
                print("|  Login erfolgreich")
                print("|")
                print(
                    "--------------------------------------------------------------------------------------"
                )
                print()
                with open("settings.txt", "w") as f:
                    f.write(f"{email}\n{row[1]}")
            else:
                print("|")
                print("|  Das Passwort ist inkorrekt")
                password = None
    else:
        print("|")
        print("|  Es existiert kein Account mit dieser E-Mail Adresse")


def create_account():
    # Delete settings.txt if it exists to avoid conflicts
    if os.path.exists("settings.txt"):
        os.remove("settings.txt")

    email = None

    # Repeat until a valid email is entered
    while email is None:
        email = input("|  Bitte geben Sie Ihre E-Mail Adresse ein: ")
        if re.match(
            r"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$", email
        ) and email.endswith("@gmail.com"):
            cur.execute(
                "SELECT password, app_code FROM account WHERE email = ?", (email,)
            )
            row = cur.fetchone()

            if row:
                print("|")
                print("|  Es existiert bereits einen Account mit dieser E-Mail Adresse")
                email = None
                continue

            app_code = None

            # Repeat until a valid app code is entered
            while app_code is None:
                app_code = input("|  Bitte geben Sie Ihren Gmail App-Code ein: ")

                if app_code == "":
                    print("|")
                    print("|  Der Gmail App-Code darf nicht leer sein")
                    app_code = None
                    continue

                password = None

                # Repeat until a valid password is entered
                while password is None:
                    password = input(
                        "|  Bitte geben Sie ein Passwort ein (wird genutzt um dich einfacher zu Authentifizieren). Das Passwort muss mindestens 8 Zeichen lang sein und mindestens einen Grossbuchstaben, einen Kleinbuchstaben, eine Ziffer und ein Sonderzeichen enthalten. (^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$): "
                    )

                    if not re.match(
                        r"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$",
                        password,
                    ):
                        print("|")
                        print(
                            "| Das Passwort muss mindestens 8 Zeichen lang sein und mindestens einen Großbuchstaben, einen Kleinbuchstaben, eine Ziffer und ein Sonderzeichen enthalten. (^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$)"
                        )
                        password = None
                        continue

                    hashed_password = bcrypt.hashpw(
                        password.encode("utf-8"), bcrypt.gensalt()
                    )

                    # Store the email, password and app_code in the database
                    cur.execute(
                        """
                        INSERT INTO account (email, password, app_code) VALUES (?, ?, ?)
                    """,
                        (email, hashed_password.decode("utf-8"), app_code),
                    )
                    con.commit()

                    with open("settings.txt", "w") as f:
                        f.write(f"{email}\n{app_code}")

                print("|")
                print("|  Account erfolgreich registriert")
                print("|")
                print(
                    "--------------------------------------------------------------------------------------"
                )
                print()
        else:
            print("|")
            print(
                "|  Diese E-Mail Adresse ist ungültig. Bitte geben Sie eine gültige E-Mail Adresse ein."
            )
            email = None


def main():
    print("|")
    print("|  Willkommen zum CLI E-Mail Tool")
    print(
        "--------------------------------------------------------------------------------------"
    )
    print("|")
    print("|  © 2024 by Levyn Schneider and David Meer")
    print("|  (Unterstützt nur Gmail)")
    print("|")

    # Check if file exists
    if not os.path.exists("settings.txt"):
        choose_login_register(None)
    else:
        with open("settings.txt", "r") as f:
            content = f.readlines()

        # Check if the content has one line
        if len(content) != 2:
            choose_login_register("Falsche settings.txt Konfiguration.")

        email = content[0].strip()
        app_code = content[1].strip()

        if email == "" or app_code == "":
            choose_login_register("Deine Konfiguration ist ungültig.")
        else:
            print(f"|  Eingeloggt als: {email}")
            print("|")
            print(
                "--------------------------------------------------------------------------------------"
            )
            print()

    # Menu Selector
    print("Bitte wählen Sie eine Option:")
    print("1 (Einstellungen)")
    print("2 (E-Mail Senden)")
    print("3 (E-Mail Empfangen)")
    print("4 (Logout)")
    print("5 (Exit)")
    print()

    option = input("Option wählen: ")

    if option == "1":
        os.system("python3 settings.py")
    elif option == "2":
        os.system("python3 send.py")
    elif option == "3":
        os.system("python3 receive.py")
    elif option == "4":
        os.remove("settings.txt")
        print("Erfolgreich ausgeloggt")
    elif option == "5":
        print()
        print("Auf Wiedersehen!")
    else:
        print("Ungültige Option")


if __name__ == "__main__":
    main()
