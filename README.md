# wormsGame

Wormagedon-like game.

# Feature

The main features of this game are:
* Pysics engine.
* Modularity to make many games.

## UML diagrams

UML files were produced with PlantText. To generate the UML diagrams, please import these files in www.planttest.com.

## GIT workflow ##

We used the following branch structure:
* Master: Stable version. Only team leader can commit it.
* Release: Feature freeze. Used to test and bug fix for production. Only team
  leader can commit it.
* Hot fixes: Work out specific issues. Initial commit should start with a
  brief description of the issue.
* Development: up-to-date branch.
* Feature: Each new feature gets its branch and is developed seperately.
  Branch name should be suggestive of the new feature to implement. Initial
commit should start with a brief description of the feature.

![GIT workflow](https://nvie.com/img/git-model@2x.png)

Example to start a new feature (playerControl is *batata*):
git checkout -b playerControl
*make sure the next steps are always performed while in this branch!*
*make first changes*
git add.
git commit -m 'Feature: Player Control. Keyboard input to class
ControlableObject.'
*more changes*
git add .
git commit -m 'All tests passed.'
*make sure the next steps are performed in the development branch.*
git checkout development
git pull origin development *make sure your local development branch is up-to-date*
git merge playerControl *merge your feature to dev branch*
*workout conflits, if needed. Provide a good merging message.*
git push origin development

