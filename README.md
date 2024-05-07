# Tucil3_13522097
Word Ladder Solver with Uniform Cost Search (UCS), Greedy Best First Search (GBFS), and A* Search

## Table of Contents

- [General Info](#general-information)
- [Technologies Used](#technologies-used)
- [Requirements](#requirements)
- [Features](#features)
- [Setup](#setup)
- [Usage](#usage)
- [Author](#author)

## General Information

Word ladder (also known as Doublets, word-links, change-the-word puzzles, paragrams, laddergrams, or word golf) is a word game invented by Lewis Carroll. A word ladder puzzle begins with two words, and to solve the puzzle one must find a chain of other words to link the two, in which two adjacent words (that is, words in successive steps) differ by one letter.

How to play?

The player is given a start word and an end word. In order to win the game, the player must change the start word into the end word progressively, creating an existing word at each step. Each step consists of a single letter substitution. For example, the following are the seven shortest solutions to the word ladder puzzle between words "cold" and "warm", using words from Collins Scrabble Words.

    COLD 	→ 	CORD 	→ 	CORM 	→ 	WORM 	→ 	WARM

    COLD 	→ 	CORD 	→ 	CARD 	→ 	WARD 	→ 	WARM

    COLD 	→ 	CORD 	→ 	WORD 	→ 	WARD 	→ 	WARM

    COLD 	→ 	CORD 	→ 	WORD 	→ 	WORM 	→ 	WARM

    COLD 	→ 	WOLD 	→ 	WORD 	→ 	WORM 	→ 	WARM

    COLD 	→ 	WOLD 	→ 	WORD 	→ 	WARD 	→ 	WARM

    COLD 	→ 	WOLD 	→ 	WALD 	→ 	WARD 	→ 	WARM 

As each step changes only one letter, the number of steps must be at least the Hamming distance between the two words – four in the above example. Lewis Carroll's example has an extra fifth step as the third letter changes twice.

Often, word ladder puzzles are created where the end word has some kind of relationship with the start word (synonymous, antonymous, semantic...). This was also the way the game was originally devised by Lewis Carroll when it first appeared in Vanity Fair

## Technologies Used

- java 21 2023-09-19 LTS
- Java(TM) SE Runtime Environment (build 21+35-LTS-2513)
- Apache Maven

## Features

- Uniform Cost Search (UCS) Algorithm
- Greedy Best First Search (GBFS) Algorithm
- A\* Search Algorithm

## Requirements

- java version "22.0.1" 2024-04-16
- Java(TM) SE Runtime Environment (build 22.0.1+8-16)
- Windows
- Linux
- Visual Studio Code

## Setup

With Visual Studio Code, you'll first need to build JavaFX project using Maven by pressing `CTRL +SHIFT + P` and select `Java: Create Java Project`. Then, select `JavaFX` which is provided by Maven. Lastly, simply configure your project as configured in the repository.

You can find more info on the VSC documentation on [java-gui](https://code.visualstudio.com/docs/java/java-gui).

## Usage
![alt-text](https://i.ibb.co/0VkM02x/Screenshot-Run.png)
1. Run with VS Code
2. Input start word
3. Input goal word
4. Choose algorithm
5. Upload the dictionary file
6. Press the `solve` button

# Reference

The dictionary that is used as reference is taken from the following  [repository](https://github.com/dwyl/english-words/blob/master/words.txt) and is also available on the test directory.

# Author
|          Name                | NIM |
|--------------------------------|------------|
| Ellijah Darrellshane Suryanegara      | 13522097  |