# Projet-CPOA - Système de Gestion RevuesOnLine

## 📝 Description du projet
Application de gestion développée en Java avec JavaFX permettant la gestion d'un système de revues en ligne. Le projet implémente un modèle DAO (Data Access Object) avec deux types de persistance de données : ListeMémoire (en mémoire) et MySQL (base de données).

## 🚀 Fonctionnalités

### ✅ Fonctionnalités implémentées
- Menu principal intuitif et complet
- Choix entre deux modes de persistance : ListeMémoire ou MySQL
- Gestion complète des données (CRUD) :
  - Ajout de données pour toutes les tables (sauf quelques problèmes avec la table Duree)
  - Suppression sécurisée avec fenêtre de confirmation
  - Modification des données pour toutes les tables
  - Affichage détaillé via double-clic pour toutes les tables
- Importation de fichiers client au format CSV

### ❌ Problèmes connus
- Valeur de `libelle_formule` de la table Durée apparaît comme "null" ou avec une valeur aléatoire après création
- La création d'une durée peut échouer dans certains cas
- L'affichage d'un visuel pour une revue n'est pas implémenté

## 🛠️ Technologies utilisées
- Java
- JavaFX pour l'interface utilisateur
- Modèle DAO (Data Access Object)
- MySQL pour la persistance en base de données

## ⚙️ Installation et configuration

### Prérequis
- Java Development Kit (JDK)
- JavaFX SDK
- Eclipse IDE (recommandé)
- MySQL (optionnel, pour utiliser la persistance en base de données)

### Configuration dans Eclipse
1. Importer le projet dans Eclipse
2. Ajouter les fichiers JAR nécessaires dans le Build Path
3. Configurer les paramètres de la VM en ajoutant :
   ```
   --module-path "CHEMIN_VERS_JAVAFX_SDK" --add-modules javafx.controls,javafx.fxml
   ```
   Remplacer `CHEMIN_VERS_JAVAFX_SDK` par le chemin d'accès à votre installation JavaFX

## 📋 Structure du projet
- `src/application` : Point d'entrée de l'application et contrôleurs
- `src/connexion` : Gestion des connexions à la base de données
- `src/dao` : Interfaces DAO pour l'accès aux données
- `src/factory` : Fabriques pour instancier les implémentations DAO
- `src/liste_memoire` : Implémentation en mémoire des DAOs
- `src/modele` : Classes modèles des objets métier
- `src/normalisation` : Utilitaires de normalisation des données
