# Snake Game with Linked List in Java

## Overview

This project is a classic implementation of the Snake game with a unique twist: the snake's body is managed as a linked list. The game is developed in Java and demonstrates the use of data structures to handle game logic, including movement, collision detection, food consumption, and a special reversal feature.

## Features

- **Linked List Implementation:** The snake's body is represented as a linked list, utilizing its properties for efficient addition and removal of segments.
- **Collision Detection:** Detects collisions with the walls and the snake's own body.
- **Food Consumption:** Randomly generates food on the game board that the snake can consume to grow.
- **Special Food:** If the snake consumes a special food, its direction is reversed by reversing the underlying linked list.
- **Responsive Controls:** Smooth and responsive control of the snake's movement using keyboard inputs.
- **Graphical Interface:** Simple and clean graphical interface using Java's Swing library.

## Installation

1. Clone the repository:
`
git clone https://github.com/Aarij00/Linked-List-Snake.git
`

2. Compile the project:
`
javac src/*.java
`

3. Run the game:
`
java -cp src Main
`
