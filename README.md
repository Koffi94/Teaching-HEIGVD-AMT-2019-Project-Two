# AMT - Projet 2

**Etudiants: Oliver Koffi, Nathanaël Mizutani**

## À propos du projet

Les spécifications peuvent être trouvées [ici](Documentation/Specifications.md).

## Manuel d'utilisation
1. Désactiver tous les services qui tournent sur les ports 3306 et 80.

2. Exécuter le script `runCompile.sh` pour compiler les projets et créer les "packages".

2. Exécuter le script `runStart` pour démarrer l'infrastructure.

4. Pour démmarer les tests cucumber, exécuter le script `runTests.sh`. L'infrastructure doit être actives pour pouvoir effectuer les tests.

5. Exécuter le script `runStop.sh` pour arrêter proprement l'infrastructure.

L'adresse `localhost:80/api/auth` permet interagir avec la partie managment de l'application, l'adresse `http://localhost/api/business` permet d'interagir avec la partie business de l'application et l'adresse `http://localhost:9090` permet d'accéder à l'interface de gestion de Traefik.

## Documentation
Le rapport détaillé du projet se trouve [ici](Documentation/report.md).
