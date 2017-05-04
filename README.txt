|*******************************************|
|				SAAB Mathieu				|
|  TP de Conception d'Application Réparties	|
|				Serveur FTP					|
|*******************************************|		

|------- Utiliser le serveur : 

Afin de lancer le serveur, il faut lancer la classe se 
situant dans src/main/Main.java

Une fois que le serveur est lancé, il faut se connecter 
à l'aide de Filezilla en s'assurant de donner comme nom 
d'hôte 127.0.0.1 (sinon il se mettra en IPv6 et lancera 
des commandes qui ne sont pas traitées) sur le port 
1025 avec l'un des deux comptes suivants :

|--- NoctisLucisCaelum
|--- LunafreyaNoxFleuret

les deux avec le mot de passe 'azerty'.

|------- Ce qui n'a pas été fait

Malheureusement, par manque de temps et surtout car j'ai 
du changer le disque dur de mon ordinateur et n'ai donc 
pas pu travailler sur le projet pendant un week end entier,  
je n'ai pas pu traiter de la manière la plus propre possible 
le problème, notamment au niveau du transfert de fichiers.

Aussi, je me suis rendu compte d'un problème avant de faire 
le rendu, lorsque le client fait un PWD on lui renvoie le 
chemin absolu alors qu'il aurait été plus judicieux de 
ne lui donner la visibilité uniquement sur le chemin dans 
lequel il est actuellement (/noctisluciscaelum par exemple)

De plus, je n'ai pas pu implémenter les commandes en mode 
actif ou encore quelques commandes qui auraient étées simples
comme DELE par exemple.

|------- Commandes traitées et fonctionnelles :
|---USER
|---PASS
|---TYPE
|---LIST
|---PASV
|---RETR
|---STOR