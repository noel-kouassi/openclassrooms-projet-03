## Installation

1 - Installer la version 17 du jdk

2 - Installer MySQL version 8.0

3 - Installer un client SQL exemple Workbench ou Dbeaver

4 - Créer une base de données et un utilisateur de votre schéma
   
5 - Attribuer les droits nécessaires à votre utilisateur sur le schéma créé

**Attention**: Ne pas effectuer de modifications manuelles directement dans la base de données. Si besoin, ajouter votre script dans le répertoire resources/db/migration

6 - Créer et affecter leurs valeurs aux variables d'environnement suivantes :
* DB_NAME
* DB_HOST
* DB_PORT (Par défaut égal à 3306)
* DB_USERNAME
* DB_PASSWORD
* JWT_SECRET_KEY
* PICTURES_DIRECTORY

7 - Installer apache maven 3.9.6

8 - Cloner le projet avec le lien github :
* Lien ssh : git@github.com:noel-kouassi/openclassrooms-projet-03.git
* Lien https : https://github.com/noel-kouassi/openclassrooms-projet-03.git

9 - Se positionner dans le répertoire du projet cloné et exécuter la commande : 
**mvn clean install**

10 - Pour le démarrage de l'application: exécuter la commande suivante :
**mvn spring-boot:run**

11 - Après le lancement de l'application, veuillez consulter le lien swagger suivant afin d'obtenir la liste des opérations possibles : http://localhost:8080/swagger-ui/index.html