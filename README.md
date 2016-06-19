# PokemonGame
This is a simple implementation of a Pokemon game which is played by console.
It is written and shared for fun, you can easily edit and make some additions.
Game has 3 modes, which are also explained in the GameController.java
run200Games: It runs 200 games automatically between all pokemons given two strategy.
- Strategy will be explained -
versusComputer : Play against computer.
multiplayer : play against an opponent.

A battle works as follows. In each turn players can take 3 actions:
Attack: decreases the opponent HP according to some rules. Rules are
explained in the Pokemon's class damageHim method.
Defense: Increases the defense point of pokemons.
Potion: Fulls the pokemon's HP. It can be used only once in a battle.
Then turn is left to opponent. battle keeps going til one of the pokemon is dead.

Game has also super power option which is basically attacking twice during each attack.
You can easily change it.

Code has two parts: brain and pokemons.
brain includes:
Strategies : Classes to determine which action will be taken by computer. You can
easily implement an AI to perform your skills.
AttackOnly : Strategy which attacks at each turn.
MyStrategy : A strategy implemented by me.
Runner : Run the game here by selecting mode.
GameController : Implements the game dynamics.

pokemon includes:
Every type of pokemon and the superclass 'pokemon'. You can change the points of
pokemons easily by altering their constructor. - Make Pikachu super awesome :)-
also add your own type.

Feel free to ask any quesitons :)
I hope you will be the very best :) 
