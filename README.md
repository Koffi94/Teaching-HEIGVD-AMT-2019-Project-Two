# AMT - Projet 2

**Etudiants: Oliver Koffi, Nathanaël Mizutani**

## À propos du projet

Les spécifications peuvent être trouvées [ici](Documentation/Specifications.md).

## Manuel d'utilisation
1. Désactiver tous les services qui tournent sur les ports 3306 et 80 de la machines.

2. Lancer le script `compile.sh` pour compiler les projets et créer les "packages".

2. Lancer la commande `docker-compose up --build`pour démarrer l'infrastructure.
Le port `localhost:80` est pour l'utilisation des API et le port `localhost:9090` pour la gestion de Traefik.

4. Pour démmarer les tests cucumber, lancer le script `runTests.sh`

## Documentation
// Lien vers le rapport
