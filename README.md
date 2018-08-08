# wormsGame

Wormagedon-like game. This game was developed during one week only, while we were having our fourth week of Java.

In this game, two teams fight in turns for supremacy on the battleground. Each soldier is armed with a bazooka, a rifle and a jetpack. Beware not to fly where the oxygen level is low!

# Features

The main features of this game are:
* Game engine, which is independent of visualization engine.
* 2D Pysics engine, built from the ground-up.
* Modular, open for expansion, close for modification.
* Easiness to add new levels and soldiers skins.

## GIT workflow ##

We used the following branch structure:
* Master: Stable version. Only team leader can commit to it.
* Release: Feature freeze. Used to test and bug fix for production. Only team
  leader can commit to it.
* Hot fixes: Work out specific issues. Initial commit should start with a
  brief description of the issue.
* Development: up-to-date branch.
* Feature: Each new feature gets its branch and is developed seperately.
  Branch name should be suggestive of the new feature to implement. Initial
commit should start with a brief description of the feature.

![GIT workflow](https://nvie.com/img/git-model@2x.png)

# Initial team of Developers

André Carreira
Pedro Ângelo Silva
Pedro Sardinha
Nuno Santos
