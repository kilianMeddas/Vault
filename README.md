# 🔐 Vault – Password Manager CLI

A simple **command-line password manager** built in Java.
Vault allows you to securely store, retrieve, list, and delete credentials using a JSON file as local storage.

Created by **Kilian Meddas**

---

## 📌 Features

* Add credentials (service, username, password)
* Retrieve stored passwords
* Delete credentials
* List all stored services and accounts
* Filter credentials by service
* JSON-based local storage (`PasswordManager.json`)
* Colored terminal output for better UX

---

## ⚙️ Tech Stack

* Java 21
* Maven
* JSON-simple (for JSON handling)

---

## 📁 Project Structure

```
Vault
│   PasswordManager.json
│   pom.xml
│
└───src
    └───main
        └───java
            Main.java
            Vault.java
            VaultManager.java
```

---

## 🚀 Installation

### 1. Clone the repository

```bash
git clone https://github.com/kilianMeddas/vault.git
cd vault
```

### 2. Build the project

```bash
mvn clean package
```

## 🧠 Usage

### 🔹 Add a credential

```bash
password add <service> <username> <password>
```

---

### 🔹 Get a password

```bash
password get <service> <username>
```

---

### 🔹 Delete credentials

```bash
password delete <service> <username>
```

---

### 🔹 List all credentials

```bash
password list
```

---

### 🔹 List by service

```bash
password list <service>
```

---

### 🔹 Help

```bash
password --help
```

---

## 💾 Data Storage

All credentials are stored locally in:

```
PasswordManager.json
```

Each entry follows this structure:

```json
{
  "service": "github",
  "username": "johndoe",
  "password": "myPassword123"
}
```

---

## ⚠️ Security Note

This project is for **educational purposes only**.

* Passwords are stored in **plain text JSON**
* Do NOT use it for real sensitive credentials
* No encryption is currently implemented

---

## 🪟 Windows Setup (Global CLI)

Create a file:

### `password.bat`

```bat
@echo off
cd /d "C:\Users\path\to\the\directory\Vault"
mvn -q exec:java -Dexec.mainClass=Main -Dexec.args="%*"
```

Then:

1. Move it to a folder like:

```
C:\Tools\VaultCLI\
```

2. Add that folder to **PATH**:

* Win + S → “Environment Variables”
* Edit system environment variables
* Environment Variables
* Path → Edit → New
* Add:

```
C:\Tools\VaultCLI\
```

3. Restart terminal

Now you can run:

```bash
password add github user pass
```

---

## 🐧 Linux Setup (Global CLI)

To use Vault like a real Linux command:

### 1. Create executable script

```bash
sudo nano /usr/local/bin/password
```

Paste:

```bash
#!/bin/bash

cd ~/Desktop/path/Vault
mvn -q exec:java -Dexec.mainClass=Main -Dexec.args="$@"
```

---

### 2. Make it executable

```bash
sudo chmod +x /usr/local/bin/password
```

---

### 3. Test it

```bash
password --help
password list
password add github user pass
```

---


## 📈 Possible Improvements

* AES encryption for passwords
* Master password authentication
* Clipboard copy feature
* Database storage instead of JSON
* Password strength generator

---

## 👨‍💻 Author

**Kilian Meddas**

---

## 📜 License

This project is open-source and free to use for learning purposes.
