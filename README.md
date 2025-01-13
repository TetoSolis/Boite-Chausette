# 💬 Application de Chat - SAE 302
## 📝 Description
Ce projet a été réalisé dans le cadre de la **SAE 302** et consiste à développer une application de chat utilisant un serveur relais pour transmettre les messages entre clients. Le serveur et les clients sont implémentés en **Java** et utilisent le protocole **UDP** pour la transmission des données.
### 👨‍💻 Auteurs
- **Yanis Dezzaz**
- **Guillaume Greder**
- **Mathis Guesdon**

***
## 🚀 Fonctionnalités
- 🔐 **Authentification utilisateur** avec login et mot de passe.
- 💬 **Messagerie en temps réel** entre utilisateurs.
- 👥 **Gestion des contacts** (ajout et suppression d'amis via un système de demande/validation).
- 🗄️ **Stockage des messages en attente** sur le serveur.
- 📜 **Historique des conversations** (les 10 derniers messages par contact stockés sur le client).

***
## 📌 Table des Matières
1. 📥 Installation
2. 🏗️ Architecture
3. 📡 Protocole applicatif
4. 🖥️ Interface graphique
5. 👨‍💻 Contributeurs
6. 📜 Licence

***
## 1. 📥 Installation
### 🔧 Prérequis
- **☕ Java 17** ou supérieur.
- Un environnement de développement (**IntelliJ IDEA**, **Eclipse**) ou un terminal avec `javac`.
### 📌 Instructions
1. **Cloner ce dépôt** :
    ``` bash
    git clone https://github.com/votre-utilisateur/votre-depot.git
    cd votre-depot
    ```
    
2. **🔨 Compiler les fichiers Java** :
    ```bash
    javac -d out src/*.java
    ```
    
3. **🚀 Lancer le serveur** :
    ```bash
    java -cp out Server
    ```
    
4. **💻 Lancer les clients** (dans des terminaux séparés) :
    ``` bash
    java -cp out Client
    ```

***
## 2. 🏗️ Architecture
Le projet est divisé en plusieurs classes pour assurer une meilleure modularité et facilité de maintenance.
### 🛠️ Composants principaux
- `📩 Msg` : Manipulation des messages (contenu, date, expéditeur, destinataire).
- `👤 Session` : Gestion des informations utilisateur côté serveur (amis, messages en attente).
- `📡 Chaussette` : Interface de communication pour l'envoi et la réception de messages via UDP.
- `🖥️ ChaussetteClient` : Surcouche simplifiant les interactions côté client.
- `🔄 ChaussetteEngine` : Gestion des messages et des amis.
### 📊 Diagramme des relations entre classes
```
Server
└── Session
    └── Msg
Client
└── ChaussetteClient
    └── Chaussette
        └── Msg
```

***
## 2. 🏗️ Architecture
Le projet est divisé en plusieurs classes pour assurer une meilleure modularité et facilité de maintenance.
### 🛠️ Composants principaux
- `📩 Msg` : Manipulation des messages (contenu, date, expéditeur, destinataire).
- `👤 Session` : Gestion des informations utilisateur côté serveur (amis, messages en attente).
- `📡 Chaussette` : Interface de communication pour l'envoi et la réception de messages via UDP.
- `🖥️ ChaussetteClient` : Surcouche simplifiant les interactions côté client.
- `🔄 ChaussetteEngine` : Gestion des messages et des amis.
### 📊 Diagramme des relations entre classes
```
Server
└── Session
    └── Msg
Client
└── ChaussetteClient
    └── Chaussette
        └── Msg
```

---
## 3. 📡 Protocole applicatif
Le protocole de communication repose sur UDP et définit plusieurs types de requêtes.
### 📑 Liste des requêtes

|🏷️ Requête|📖 Description|📝 Format|
|---|---|---|
|`⚠️ NOTIFY`|Informe d'une erreur ou d'une boîte vide|`NOTIFY,code`|
|`🔄 UPDATE`|Récupère les messages en attente|`UPDATE,mdp,login,code`|
|`📤 SEND`|Envoie un message ou une demande d'ami|`SEND,mdp,from,to,date,content`|
|`📨 FORWARD`|Transmet les messages en attente d'un utilisateur|`FORWARD,from,date,content`|

### 👫 Exemple d'ajout d'ami
1. Un utilisateur envoie une requête `SEND` avec `content = /rqstFrd` pour demander un ami.
2. Le destinataire accepte la demande en envoyant `SEND` avec `content = /accptFrd`.

***
## 4. 🖥️ Interface graphique
L'interface graphique a été développée avec **Swing** et comprend trois écrans principaux :

1. **🔑 Page de connexion** : Permet la saisie du login et du mot de passe.
2. **💬 Fenêtre de discussion** : Affiche les messages et permet l'envoi en temps réel.
3. **👥 Gestion des contacts** : Ajout et suppression d'amis
 ***
## 5. 👨‍💻 Contributeurs
- **🧑‍💻 Yanis Dezzaz**
- **👨‍💻 Guillaume Greder**
- **🧑‍💻 Mathis Guesdon**

***
## 6. 📜 Licence
Ce projet est distribué sous la licence **MIT**. Vous êtes libre de l'utiliser, le modifier et le redistribuer selon les termes de cette licence.

***

### ⭐ N'hésitez pas à mettre une **étoile** ce dépôt si vous trouvez ce projet utile ! 🚀
