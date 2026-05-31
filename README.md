# 🔐 Vault – Password Manager CLI

A simple **command-line password manager** built in Java.  
Vault allows you to securely store, retrieve, list, and delete credentials using a JSON file as local storage.

Created by **Kilian Meddas**

---

## 📌 Features

- Add credentials (service, username, password)
- Retrieve stored passwords
- Delete credentials
- List all stored services and accounts
- Filter credentials by service
- JSON-based local storage (`PasswordManager.json`)
- Colored terminal output for better UX

---

## ⚙️ Tech Stack

- Java 21
- Maven
- JSON-simple (for JSON handling)

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

````

---

## 🚀 Installation

### 1. Clone the repository

```bash
git clone https://github.com/kilianMeddas/vault.git
cd vault
````

### 2. Build the project

```bash
mvn clean package
```

### 3. Run the application

```bash
java -jar target/Vault-1.0-SNAPSHOT.jar
```

---

## 🧠 Usage

### 🔹 Add a credential

```bash
password add <service> <username> <password>
```

Example:

```bash
password add github johndoe myPassword123
```

---

### 🔹 Get a password

```bash
password get <service> <username>
```

Example:

```bash
password get github johndoe
```

---

### 🔹 Delete credentials

```bash
password delete <service> <username>
```

Example:

```bash
password delete github johndoe
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

Example:

```bash
password list github
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

## 📈 Possible Improvements

* AES encryption for passwords
* Master password authentication
* Search command
* Clipboard copy feature
* GUI version (JavaFX)
* Database storage instead of JSON
* Password strength generator

---

## 👨‍💻 Author

**Kilian Meddas**

---

## 📜 License

This project is open-source and free to use for learning purposes.
