# Project #2: NFA

* Author: James Hyle, Rebecca Berg
* Class: CS 361 
* Semester: Fall 2025

## Overview

This program models a turing machine using a bi-infinite tape. The program parses a Turing Machine state transition 
configuration and simulates the processing of the input string, and then prints the output, length, and sum of 
symbols on the tape.

## Reflection

This project was a bit easier than P2 after getting started, but required some shifting in thinking about how the 
program operates on the input from P1 and P2. Initially it was a challenge setting up the project from scratch with 
no starter files to base the outline upon. It was good practice delegating the responsibilities for each piece of 
the turing machine using object oriented principles because it made implementing changes much easier to see in the 
code, and follow what the methods are actually doing. For example, upon initially getting the program to run and 
produce output, we had an extremely slow runtime when running the large configuration (file5.txt). Rebecca saw a 
great opportunity to optimize the runtime of the program by using a list iterator for our tape, resulting in our 
program speeding up by a substantial amount. This in turn also made our tape movement code much more readable in 
comparison to keeping an numerical index. Because our code was already encapsulated this was simple to implement and 
had a massive impact on our performance. Overall we are happy with the performance of our program and have a 
competitive implementation for the extra credit runtime competition. 

## Compiling and Using

### To compile execute the following command in the project directory: 
javac tm/*.java

### To run the program once compiled use the following: 
File0: "java tm.TMSimulator file0.txt:

File2: "java tm.TMSimulator file2.txt"

File5: "java tm.TMSimulator file5.txt"

## Sources used